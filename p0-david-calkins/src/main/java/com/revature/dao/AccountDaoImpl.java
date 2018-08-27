package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.server.DBConnectionUtil;

public class AccountDaoImpl implements AccountDao {
	
	/** The default logging object. */
	private static Logger log = Logger.getRootLogger();
	
	@Override
	public Account getAccountById(long id, Connection con) throws SQLException {
		Account a = null;
		final String sql = "SELECT * FROM ACCOUNTS WHERE accId = ?";
		
		// A result set for iterating through the results
		ResultSet rs = null;
		
		// Attempt to open a prepared statement, bind the variables, and get 
		// an account. 
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			// Bind the variable to the query
			ps.setLong(1, id);
			
			// Keep in mind, this needs to be closed 
			rs = ps.executeQuery();
			
			// If there is a result, get it and return it. 
			if (rs.next()) {
				a = new Account();
				a.setAccId(rs.getLong("accId"));
				a.setAccType(rs.getString("accType"));
				a.setBalance(rs.getBigDecimal("balance"));
			}
		} finally {
			// Make an attempt at closing resources. Do nothing if closing fails
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
		}
		
		return a;
	} // end of getAccount

	
	@Override
	public List<Account> getAccounts(Connection con) throws SQLException {
		List<Account> accountList = new ArrayList<>();
		Account a = null;
		String sql = "SELECT * FROM ACCOUNTS";
		
		// A result set for iterating through the results
		ResultSet rs = null;
		
		// Attempt to create a statement and execute it. 
		try (Statement st = con.createStatement()){
			// Keep in mind, this needs to be closed 
			rs = st.executeQuery(sql);
			
			// While there are more results, get them, and add them to the list.
			while (rs.next()) {
				a = new Account();
				a.setAccId(rs.getLong("accId"));
				a.setAccType(rs.getString("accType"));
				a.setBalance(rs.getBigDecimal("balance"));
				accountList.add(a);
			}
		} finally {
			// Try to close the result set
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
		}
		
		return accountList;
	} // end of getAccounts
	
	
	
	@Override
	public int createAccount(Account account, Connection con) throws SQLException {
		String sql = "INSERT INTO ACCOUNTS (accId, accType, balance) VALUES (?, ?, ?)";
		//The number of affected rows by this insertion. 
		int rowsAffected = 0;
		
		// Connection object
		Connection con = null;
		// An object for using a Prepared SQL Statement
		PreparedStatement ps = null;
		
		try {
			// Open the connection to the database
			con = DBConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, account.getAccId());
			ps.setString(2, account.getAccType());
			ps.setBigDecimal(3, account.getBalance());
			
			rowsAffected = ps.executeUpdate();
			
		} catch (IOException | SQLException e) {
			// Log an error if it occurs
			log.error(e);
		} finally {
			// Try to close the prepared statement
			try {if (ps!=null) ps.close();} catch(SQLException e) {}
			// Try to close the connection
			try {if (con!=null) con.close();} catch(SQLException e) {}
		}
		
		return rowsAffected;
	}

	@Override
	public int updateAccount(Account account, Connection con) throws SQLException {
		String sql = "UPDATE ACCOUNTS SET accType=?, balance=? WHERE accId = " 
					+ account.getAccId();
		return modifyAccount(account, sql);
	}

	@Override
	public int deleteAccount(long id, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAccount(Account account, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	/** Takes an account, and an sql string for a prepared statement, and 
	 * updates the account with the corresponding id.  */
	private int modifyAccount(Account account, String sql) {
		//The number of affected rows by this insertion. 
		int rowsAffected = 0;
		
		// Connection object
		Connection con = null;
		// An object for using a Prepared SQL Statement
		PreparedStatement ps = null;
		
		try {
			// Open the connection to the database
			con = DBConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, account.getAccType());
			ps.setBigDecimal(2, account.getBalance());
			
			rowsAffected = ps.executeUpdate();
			
		} catch (IOException | SQLException e) {
			// Log an error if it occurs
			log.error(e);
		} finally {
			// Try to close the prepared statement
			try {if (ps!=null) ps.close();} catch(SQLException e) {}
			// Try to close the connection
			try {if (con!=null) con.close();} catch(SQLException e) {}
		}
		
		return rowsAffected;
	}
	
}

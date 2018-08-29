package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class AccountDaoImpl implements AccountDao {
	
	/** The default logging object. */
//	private static Logger log = Logger.getRootLogger();
	
	private static final String ACCOUNT_ID_STR = "accId";
	private static final String ACCOUNT_TYPE_STR = "accType";
	private static final String BALANCE_STR = "balance";
	
	
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
				a.setAccId(rs.getLong("ACCOUNT_ID_STR"));
				a.setAccType(rs.getString(ACCOUNT_TYPE_STR));
				a.setBalance(rs.getBigDecimal(BALANCE_STR));
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
		final String sql = "SELECT * FROM ACCOUNTS";
		
		// A result set for iterating through the results
		ResultSet rs = null;
		
		// Attempt to create a statement and execute it. 
		try (Statement st = con.createStatement()){
			// Keep in mind, this needs to be closed 
			rs = st.executeQuery(sql);
			
			// While there are more results, get them, and add them to the list.
			while (rs.next()) {
				a = new Account();
				a.setAccId(rs.getLong(ACCOUNT_ID_STR));
				a.setAccType(rs.getString(ACCOUNT_TYPE_STR));
				a.setBalance(rs.getBigDecimal(BALANCE_STR));
				accountList.add(a);
			}
		} finally {
			// Try to close the result set
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
		}
		
		return accountList;
	} // end of getAccounts
	
	
	
	@Override
	public long createAccount(Account account, Connection con) throws SQLException {
		final String sql = 
				"INSERT INTO ACCOUNTS (accId, accType, balance) VALUES (?, ?, ?)";
		// Used to hold the key from the generated object
		long key = -1;
		
		// A result set for getting the generated key
		ResultSet rs = null;
		
		// This is necessary as OracleDB doesn't properly return the generated 
		// key when using the Statement.RETURN_GENERATED_KEYS flag in a 
		// statement. 
		String[] keyName = {ACCOUNT_ID_STR};
		
		// Attempt to create a statement and execute it. 
		try (PreparedStatement ps = con.prepareStatement(sql, keyName)) {
			// Bind the variables to the statement
			ps.setLong(1, account.getAccId());
			ps.setString(2, account.getAccType());
			ps.setBigDecimal(3, account.getBalance());
			
			ps.executeUpdate();
			
			// Get the key that was generated from the insertion of the account
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getLong(1);
				
				// Also set the account id 
				account.setAccId(key);
			}
		} finally {
			// Try to close the result set
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
		}
		
		return key;
	} // end of createAccount
	
	
	@Override
	public int updateAccount(Account account, Connection con) 
											throws SQLException 
	{
		final String sql = 
				"UPDATE ACCOUNTS SET accType=?, balance=? WHERE accId = " 
					+ account.getAccId();
		//The number of affected rows by this insertion. 
		int rowsAffected = 0;
		
		// Attempt to create a statement and execute it. 
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			// Bind the variables to the statement
			ps.setString(1, account.getAccType());
			ps.setBigDecimal(2, account.getBalance());
			
			rowsAffected = ps.executeUpdate();
		} finally {
			// Do nothing. Just here to allow the autoclosing of the 
			// prepared statement. 
		}
		
		return rowsAffected;
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
	
	
} // end of class AccountDaoImpl



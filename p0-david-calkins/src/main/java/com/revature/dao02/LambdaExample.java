package com.revature.dao02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.Account;
import com.revature.dao02.OldAccountDao;
import com.revature.server.query.PreparedExecutor;

public class LambdaExample implements OldAccountDao {
	
	
	@Override
	public Account getAccountById(long id, Connection con) throws SQLException {
		String sql = "SELECT * FROM ACCOUNTS WHERE accId = ?";
		
		// Create a prepared statement that binds and executes our sql. 
		PreparedExecutor<Long> prepExecutor = 
				(PreparedStatement ps, Long accId) -> {
					// Bind the prepared statement
					ps.setLong(1, id);
					
					// Execute the query. This will be closed when the 
					// statement is closed. 
					ResultSet rs = ps.executeQuery();
					
					// If there is a result, get it and return the account
					if (rs.next()) {
						Account a = new Account();
						a.setAccId(id);
						a.setAccType(rs.getString("accType"));
						a.setBalance(rs.getBigDecimal("balance"));
						return a;
					}
					
					return null;
				};
		
		
		// Pass in the query, to be executed
		return (Account) 
				executePreparedQuery(con, sql, Long.valueOf(id), prepExecutor);
	}

	@Override
	public List<Account> getAccounts(Connection con) throws SQLException {
		// A list to return the accounts with
		List<Account> accountList = new ArrayList<>();
		Account a = null;
		String sql = "SELECT * FROM ACCOUNTS";
		
		// A result set for iterating through the results
		ResultSet rs = null;
		
		try (Statement st = con.createStatement()){
			// Get the result set of the data query
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
	}

	@Override
	public long createAccount(Account account, Connection con) throws SQLException {
		return 0;
	}

	@Override
	public int updateAccount(Account account, Connection con) throws SQLException {
		return 0;
	}

	@Override
	public int deleteAccount(long id, Connection con) throws SQLException {
		return 0;
	}

	@Override
	public int deleteAccount(Account account, Connection con) throws SQLException {
		return 0;
	}

	
	
	
	/** 
	 * A private helper method that is used to facilitate the execution of a 
	 * query or an update.
	 * @param con
	 * @param sql
	 * @param bindInfo
	 * @param binderExecutor
	 * @return
	 * @throws SQLException - If an SQL exception occurrs.
	 */
	private Object executePreparedQuery(Connection con, 
										String sql, 
										Object bindInfo, 
										PreparedExecutor binderExecutor) 
												throws SQLException 
	{
		// An object for using a Prepared SQL Statement
		PreparedStatement ps = null;
		
		try {
			// Open the connection to the database
			ps = con.prepareStatement(sql);
			
			// Bind the values, execute the query, and return the result. 
			return binderExecutor.bindAndExecute(ps, bindInfo);
		} finally {
			// Make an attempt at closing resources. Do nothing if closing fails
			// Try to close the prepared statement
			try {if (ps!=null) ps.close();} catch(SQLException e) {}
		}
		
	}
	
}

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

public class ClientToAccountDaoImpl implements ClientToAccountDao {
	
	/** The default logging object. */
	private static Logger log = Logger.getRootLogger();
	
	@Override
	public List<Account> getAccountsByClient(Client client) {
		List<Account> accountList = new ArrayList<>();
		Account a = null;
		String sql = "SELECT * FROM Clients_to_Accounts CA"
				+ " JOIN ACCOUNTS A ON CA.accId = A.accId"
				+ "	WHERE clientId = ?";
		
		// Connection object
		Connection con = null;
		// An object for using a Prepared SQL Statement
		PreparedStatement ps = null;
		// A result set for iterating through the results
		ResultSet rs = null;
		
		try {
			// Open the connection to the database
			con = DBConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, client.getClientId());
			
			// Keep in mind, this needs to be closed 
			rs = ps.executeQuery();
			
			// While there are more results, get them, and add them to the list.
			while (rs.next()) {
				a = new Account();
				a.setAccId(rs.getLong("accId"));
				a.setAccType(rs.getString("accType"));
				a.setBalance(rs.getBigDecimal("balance"));
				accountList.add(a);
			}
		} catch (IOException | SQLException e) {
			// Log an error if it occurs
			log.error(e);
		} finally {
			// Try to close the result set
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
			// Try to close the prepared statement
			try {if (ps!=null) ps.close();} catch(SQLException e) {}
			// Try to close the connection
			try {if (con!=null) con.close();} catch(SQLException e) {}
		}
		
		return accountList;
	}

	@Override
	public List<Client> getClientsByAccount(Account account) {
		List<Client> clientList = new ArrayList<>();
		Client c = null;
		String sql = "SELECT * FROM Clients_to_Accounts CA"
				+ " JOIN CLIENTS C ON CA.clientId = C.clientId"
				+ "	WHERE accId = ?";
		
		// Connection object
		Connection con = null;
		// An object for using a Prepared SQL Statement
		PreparedStatement ps = null;
		// A result set for iterating through the results
		ResultSet rs = null;
		
		try {
			// Open the connection to the database
			con = DBConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, account.getAccId());
			
			// Keep in mind, this needs to be closed 
			rs = ps.executeQuery();
			
			// While there are more results, get them, and add them to the list.
			while (rs.next()) {
				c = new Client();
				c.setClientId(rs.getLong("clientId"));
				c.setEmail(rs.getString("email"));
				c.setUsername(rs.getString("username"));
				c.setPassPhrase(rs.getString("passPhrase"));
				clientList.add(c);
			}
		} catch (IOException | SQLException e) {
			// Log an error if it occurs
			log.error(e);
		} finally {
			// Try to close the result set
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
			// Try to close the prepared statement
			try {if (ps!=null) ps.close();} catch(SQLException e) {}
			// Try to close the connection
			try {if (con!=null) con.close();} catch(SQLException e) {}
		}
		
		return clientList;
	}

	
	
	@Override
	public int createCLtoAC(Client client, Account account) {
		String sql = "INSERT INTO Clients_to_Accounts (clientId, accId)"
				+ " VALUES (?, ?)";
		
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
			ps.setLong(1, client.getClientId());
			ps.setLong(2, account.getAccId());
			
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
	public int deleteCLtoAC(long clientId, long accountId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCLtoAC(Client client, Account account) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	


}

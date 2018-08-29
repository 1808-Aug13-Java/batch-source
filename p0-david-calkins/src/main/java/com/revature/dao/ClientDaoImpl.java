package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ClientDaoImpl implements ClientDao {
	
	/** The default logging object. */
//	private static Logger log = Logger.getRootLogger();

	@Override
	public Client getClientById(long id, Connection con)
			throws SQLException 
	{
		return getClient("clientId", Long.toString(id), con);
	}
	
	
	@Override
	public Client getClientByEmail(String email, Connection con)
			throws SQLException 
	{
		return getClient("email", email, con);
	}


	@Override
	public Client getClientByUsername(String username, Connection con)
			throws SQLException 
	{
		return getClient("username", username, con);
	}
	
	
	/** Provided a column name and a string, searches the sql database for 
	 * a client that matches the specified input. 
	 * @param column - The column to search in
	 * @param value - The value to search the column for. */
	private Client getClient(String column, String value, Connection con)
														throws SQLException 
	{
		Client c = null;
		final String sql = "SELECT * FROM CLIENTS WHERE " + column + " = ?";
		
		// A result set for iterating through the results
		ResultSet rs = null;
		
		// Attempt to create a statement and execute it. 
		try (PreparedStatement ps = con.prepareStatement(sql)){
			// Bind the variable to the query
			ps.setString(1, value);
			
			// Keep in mind, this needs to be closed 
			rs = ps.executeQuery();
			
			// If there is a result, get it and return it. 
			if (rs.next()) {
				c = new Client();
				c.setClientId(rs.getLong("clientId"));
				c.setEmail(rs.getString("email"));
				c.setUsername(rs.getString("username"));
				c.setPassPhrase(rs.getString("passPhrase"));
			}
		} finally {
			// Make an attempt at closing resources. Do nothing if closing fails
			// Try to close the result set
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
		}
		
		return c;
	}
	
	
	
	

	@Override
	public List<Client> getClients(Connection con) throws SQLException {
		List<Client> clientList = new ArrayList<>();
		Client c = null;
		final String sql = "SELECT * FROM CLIENTS";
		
		// A result set for iterating through the results
		ResultSet rs = null;
		
		// Attempt to create a statement and execute it. 
		try (Statement st = con.createStatement()){
			// Keep in mind, this needs to be closed 
			rs = st.executeQuery(sql);
			
			// While there are more results, get them, and add them to the list.
			while (rs.next()) {
				c = new Client();
				c.setClientId(rs.getLong("clientId"));
				c.setEmail(rs.getString("email"));
				c.setUsername(rs.getString("username"));
				c.setPassPhrase(rs.getString("passPhrase"));
				clientList.add(c);
			}
		} finally {
			// Try to close the result set
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
		}
		
		return clientList;
	}

	@Override
	public long createClient(Client client, Connection con) 
													throws SQLException
	{
		final String sql = "INSERT INTO CLIENTS (email, username, passPhrase)"
				+ " VALUES (?, ?, ?)";
		// Used to hold the key from the generated object
		long key = -1;
		
		// A result set for getting the generated key
		ResultSet rs = null;
		
		// This is necessary as OracleDB doesn't properly return the generated 
		// key when using the Statement.RETURN_GENERATED_KEYS flag in a 
		// statement. 
		String[] keyName = {"clientID"};
		
		// Attempt to create a statement and execute it. 
		try (PreparedStatement ps = con.prepareStatement(sql, keyName)) {
			// Bind the variables to the statement
//			ps.setLong(1, client.getClientId());
			ps.setString(1, client.getEmail());
			ps.setString(2, client.getUsername());
			ps.setString(3, client.getPassPhrase());
			
			ps.executeUpdate();
			
			// Get the key that was generated from the insertion of the account
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getLong(1);
				
				// Also set the client's key
				client.setClientId(key);
			}
			
		} finally {
			// Try to close the result set
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
		}
		
		return key;
	}

	@Override
	public int updateClient(Client client, Connection con) throws SQLException {
		final String sql = "UPDATE CLIENTS SET email=?, username=?, passPhrase=? "
				+ "WHERE clientId = " + client.getClientId();
		
		//The number of affected rows by this insertion. 
		int rowsAffected = 0;
		
		// Attempt to create a statement and execute it. 
		try (PreparedStatement ps = con.prepareStatement(sql)){
			// Bind the variables to the statement
			ps.setString(1, client.getEmail());
			ps.setString(2, client.getUsername());
			ps.setString(3, client.getPassPhrase());
			
			rowsAffected = ps.executeUpdate();
			
		} finally {
			// Do nothing. This is just here to allow auto closing of the 
			// prepared statement. 
		}
		
		return rowsAffected;
	}
	
	
	@Override
	public int deleteClient(long id, Connection con) throws SQLException {
		// Make use of a stored procedure. 
		final String sql = "{call PR_DELETE_CLIENT(?)}";
		
		//The number of affected rows by this insertion. 
		int rowsAffected = 0;
		
		// Attempt to create a statement and execute it. 
		try (CallableStatement cs = con.prepareCall(sql)) {
			// Bind the variable to the query
			cs.setLong(1, id);
			
			rowsAffected = cs.executeUpdate();
			
		}
		
		return rowsAffected;
	}
	
	
	@Override
	public int deleteClient(Client client, Connection con) throws SQLException {
		return deleteClient(client.getClientId(), con);
	}
	


	
} // end of class ClientDaoImpl

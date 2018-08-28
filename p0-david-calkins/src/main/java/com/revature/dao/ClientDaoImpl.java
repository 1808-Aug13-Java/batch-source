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

public class ClientDaoImpl implements ClientDao {
	
	/** The default logging object. */
	private static Logger log = Logger.getRootLogger();

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
		String sql = "SELECT * FROM CLIENTS WHERE " + column + " = ?";
		
		// A result set for iterating through the results
		ResultSet rs = null;
		
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
		String sql = "SELECT * FROM CLIENTS";
		
		// A result set for iterating through the results
		ResultSet rs = null;
		
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
		String sql = "INSERT INTO CLIENTS (email, username, passPhrase)"
				+ " VALUES (?, ?, ?)";
		// Used to hold the key from the generated object
		long key = -1;
		
		// A result set for getting the generated key
		ResultSet rs = null;
		
		// This is necessary as OracleDB doesn't properly return the generated 
		// key when using the Statement.RETURN_GENERATED_KEYS flag in a 
		// statement. 
		String[] keyName = {"clientID"};
		
		
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
	public int updateClient(Client client, Connection con) {
		// TODO: Finish updating this function. 
		String sql = "UPDATE CLIENTS SET email=?, username=?, passPhrase=? "
				+ "WHERE clientId = " + client.getClientId();
		return modifyClient(client, sql);
	}

	@Override
	public int deleteClient(long id, Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClient(Client client, Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	/** Takes a client, and an sql string for a prepared statement, and 
	 * updates the client with the corresponding id.  */
	private int modifyClient(Client client, String sql) {
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
			log.info(sql);
			log.info("Email:" + client.getEmail()+ "  Username:" + client.getUsername());
			ps.setString(1, client.getEmail());
			ps.setString(2, client.getUsername());
			ps.setString(3, "1234567890123456789012345678901234567890123456789012345678901234");
			
			rowsAffected = ps.executeUpdate();
			
		} catch (IOException | SQLException e) {
			// Log an error if it occurs
			log.error(e);
			e.printStackTrace();
		} finally {
			// Try to close the prepared statement
			try {if (ps!=null) ps.close();} catch(SQLException e) {}
			// Try to close the connection
			try {if (con!=null) con.close();} catch(SQLException e) {}
		}
		
		return rowsAffected;
	}


	
}

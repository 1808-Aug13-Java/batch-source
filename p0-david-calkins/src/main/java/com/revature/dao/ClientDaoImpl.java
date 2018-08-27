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
	public Client getClientById(long id) {
		return getClient("clientId", Long.toString(id));
	}
	
	
	@Override
	public Client getClientByEmail(String email) {
		return getClient("email", email);
	}


	@Override
	public Client getClientByUsername(String username) {
		return getClient("username", username);
	}
	
	
	/** Provided a column name and a string, searches the sql database for 
	 * a client that matches the specified input. 
	 * @param column - The column to search in
	 * @param value - The value to search the column for. */
	private Client getClient(String column, String value) {
		Client c = null;
		String sql = "SELECT * FROM CLIENTS WHERE " + column + " = ?";
		
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
		} catch (IOException | SQLException e) {
			log.error(e);
		} finally {
			// Make an attempt at closing resources. Do nothing if closing fails
			// Try to close the result set
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
			// Try to close the prepared statement
			try {if (ps!=null) ps.close();} catch(SQLException e) {}
			// Try to close the connection
			try {if (con!=null) con.close();} catch(SQLException e) {}
		}
		
		return c;
	}
	
	
	
	

	@Override
	public List<Client> getClients() {
		List<Client> clientList = new ArrayList<>();
		Client c = null;
		String sql = "SELECT * FROM CLIENTS";
		
		// Connection object
		Connection con = null;
		// An object for using an SQL Statement
		Statement st = null;
		// A result set for iterating through the results
		ResultSet rs = null;
		
		try {
			// Open the connection to the database
			con = DBConnectionUtil.getConnection();
			st = con.createStatement();
			
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
		} catch (IOException | SQLException e) {
			// Log an error if it occurs
			log.error(e);
		} finally {
			// Try to close the result set
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
			// Try to close the prepared statement
			try {if (st!=null) st.close();} catch(SQLException e) {}
			// Try to close the connection
			try {if (con!=null) con.close();} catch(SQLException e) {}
		}
		
		return clientList;
	}

	@Override
	public int createClient(Client client) {
		// TODO: Solve false 'ORA-01008: not all variables bound' when using 
		// a prepared statement on 
		String sql = "INSERT INTO CLIENTS (clientId, email, username, passPhrase)"
				+ " VALUES (?, ?, ?, ?)";
//		String sql = "INSERT INTO CLIENTS (email, username, passPhrase) VALUES ("
//				+ client.getEmail() + ", " 
//				+ client.getUsername() + ", " 
//				+ client.getPassPhrase() + ")";
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
			ps.setLong(1, client.getClientId());
			ps.setString(2, client.getEmail());
			ps.setString(3, client.getUsername());
			ps.setString(4, client.getPassPhrase());
			
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

	@Override
	public int updateClient(Client client) {
		String sql = "UPDATE CLIENTS SET email=?, username=?, passPhrase=? "
				+ "WHERE clientId = " + client.getClientId();
		return modifyClient(client, sql);
	}

	@Override
	public int deleteClient(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClient(Client client) {
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

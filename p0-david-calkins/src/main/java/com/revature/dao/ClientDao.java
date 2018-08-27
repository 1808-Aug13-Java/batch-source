package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * A Data Access Object interface for use in accessing bank clients. 
 */
public interface ClientDao {
	
	/** Returns a client by the given client ID. Returns null if there is 
	 * no client with the provided ID. 
	 * @return A client from the database that matches the provided id, or 
	 * null if no such client exists.
	 * @throws SQLException - If a problem occurs while running the query. */
	public Client getClientById(long id, Connection con) throws SQLException;
	
	/** Returns a client by the given client email. Returns null if there is 
	 * no client with the provided email. 
	 * @return A client from the database that matches the provided email, or 
	 * null if no such client exists.
	 * @throws SQLException - If a problem occurs while running the query. */
	public Client getClientByEmail(String email, Connection con) throws SQLException;
	
	/** Returns a client by the given client username. Returns null if there is 
	 * no client with the provided username. 
	 * @return A client from the database that matches the provided username, or 
	 * null if no such client exists.
	 * @throws SQLException - If a problem occurs while running the query. */
	public Client getClientByUsername(String username, Connection con) throws SQLException;
	
	/** Returns a list of all bank clients in the database.  
	 * @return A list of all bank clients
	 * @throws SQLException - If a problem occurs while running the query. */
	public List<Client> getClients(Connection con) throws SQLException;
	
	
	/** Inserts the corresponding information from the provided client object
	 * into the database. Returns the unique id that was generated from the 
	 * insertion.  
	 * @return The id of the inserted account
	 * @throws SQLException - If a problem occurs while running the query. */
	public long createClient(Client client, Connection con) throws SQLException;
	
	/** Updates the specified client on the server end. Adjusts the client 
	 * specified by the client id of the provided account object. 
	 * @return The number of rows affected. 
	 * @throws SQLException - If a problem occurs while running the query. */
	public int updateClient(Client client, Connection con) throws SQLException;
	
	/** Deletes the specified client on the server end, based on the id.
	 * @return The number of rows affected. 
	 * @throws SQLException - If a problem occurs while running the query. */
	public int deleteClient(long id, Connection con) throws SQLException;
	
	/** Deletes the specified client on the server end, based on the id of the 
	 * client object.
	 * @return The number of rows affected. 
	 * @throws SQLException - If a problem occurs while running the query. */
	public int deleteClient(Client client, Connection con) throws SQLException;
}

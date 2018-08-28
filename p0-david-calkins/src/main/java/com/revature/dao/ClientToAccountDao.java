package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ClientToAccountDao {
	
	/** 
	 * Returns a list of accounts that are associated with the provided client. 
	 * @param client - The client to get the accounts of 
	 * @param con - The database connection to query on
	 * @return A list of associated accounts
	 * @throws SQLException - If there is a problem executing the query
	 */
	public List<Account> getAccountsByClient(Client client, Connection con)
														throws SQLException;
	
	/** 
	 * Returns a list of clients that are associated with the provided account. 
	 * @param client - The account to get the clients of 
	 * @param con - The database connection to query on
	 * @return A list of associated clients
	 * @throws SQLException - If there is a problem executing the query
	 */
	public List<Client> getClientsByAccount(Account account, Connection con)
														throws SQLException;
	
	/** 
	 * Creates an association between the provided client and account based 
	 * on their respective IDs. 
	 * @param client - The client to associate
	 * @param account - The account to associate
	 * @param con - The database connection to use
	 * @return The number of rows affected. 
	 * @throws SQLException - If there is a problem executing the query
	 */
	public int createCLtoAC(Client client, Account account, Connection con)
														throws SQLException;
	
	/** 
	 * Deletes an association between the provided client and account based 
	 * on their respective IDs. 
	 * @param client - The client to delete the association of
	 * @param account - The account to delete the association of
	 * @param con - The database connection to use
	 * @return The number of rows affected. 
	 * @throws SQLException - If there is a problem executing the query
	 */
	public int deleteCLtoAC(long clientId, long accountId, Connection con)
														throws SQLException;
	
	/** 
	 * Deletes an association between the provided client and account based 
	 * on their respective IDs. 
	 * @param client - The client to delete the association of
	 * @param account - The account to delete the association of
	 * @param con - The database connection to use
	 * @return The number of rows affected. 
	 * @throws SQLException - If there is a problem executing the query
	 */
	public int deleteCLtoAC(Client client, Account account, Connection con)
														throws SQLException;
}

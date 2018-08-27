package com.revature.dao02;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.Account;


/**
 * A Data Access Object interface for use in accessing bank accounts. 
 */

public interface OldAccountDao {
	
	/** Returns an account by the given account ID. Returns null if there is 
	 * no account with the provided ID. 
	 * @return An account from the database that matches the provided id, or 
	 * null if no such account exists.
	 * @throws SQLException - If a problem occurs while running the query. */
	public Account getAccountById(long id, Connection con) throws SQLException;
	
	/** Returns a list of all bank accounts in the database.  
	 * @return A list of all bank accounts
	 * @throws SQLException - If a problem occurs while running the query. */
	public List<Account> getAccounts(Connection con) throws SQLException;
	
	
	/** Inserts the corresponding information from the provided account object
	 * into the database. Returns the unique id that was generated from the 
	 * insertion.  
	 * @return The id of the inserted account
	 * @throws SQLException - If a problem occurs while running the query. */
	public long createAccount(Account account, Connection con) throws SQLException;
	
	/** Updates the specified account on the server end. Adjusts the account 
	 * specified by the account id of the provided account object. 
	 * @return The number of rows affected. 
	 * @throws SQLException - If a problem occurs while running the query. */
	public int updateAccount(Account account, Connection con) throws SQLException;
	
	/** Deletes the specified account on the server end, based on the id.
	 * @return The number of rows affected. 
	 * @throws SQLException - If a problem occurs while running the query. */
	public int deleteAccount(long id, Connection con) throws SQLException;
	
	/** Deletes the specified account on the server end, based on the id of the 
	 * account object.
	 * @return The number of rows affected. 
	 * @throws SQLException - If a problem occurs while running the query. */
	public int deleteAccount(Account account, Connection con) throws SQLException;
	
} // end of interface AccountDao





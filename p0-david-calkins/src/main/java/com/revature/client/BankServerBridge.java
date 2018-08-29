package com.revature.client;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.client.data.BankUserData;
import com.revature.client.data.DataInterface;
import com.revature.dao.Account;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.Client;
import com.revature.dao.ClientDao;
import com.revature.dao.ClientDaoImpl;
import com.revature.dao.ClientToAccountDao;
import com.revature.dao.ClientToAccountDaoImpl;
import com.revature.server.DBConnectionUtil;


/** This class serves as a temporary bridge from the old client to the new 
 * DAO model of database connection. As DAO sorely needs redesigning, this 
 * will only be temporary. */
public class BankServerBridge implements DataInterface {
	
	
	/** The default logging object. */
	private static Logger log = Logger.getRootLogger();
	
	private static final String DEFAULT_ACCOUNT = "Checking";
	
	
	@Override
	public boolean userNameExists(String username) 
											throws IOException, SQLException 
	{
		// Get an autoclosable connection
		try (Connection con = DBConnectionUtil.getConnection()) {
			return userNameExists(username, con);
		}
	} // end of usernameExists
	
	
	public boolean userNameExists(String username, Connection con) 
											throws SQLException 
	{
		ClientDao clientDao = new ClientDaoImpl();
		
		return clientDao.getClientByUsername(username, con) != null;
	} // end of usernameExists
	

	// Returns null if there is no client or account associated with that username
	@Override
	public BankUserData getUserByName(String username) 
											throws IOException, SQLException 
	{
		// Get an autoclosable connection
		try (Connection con = DBConnectionUtil.getConnection()) {
			// Get the information about the user from the server
			Client client = getClientByUsername(username, con);
			if (client == null) {
				return null;
			}
			
			Account account = getAccount(client, con);
			if (account == null) {
				return null;
			}
			
			return toBankUserData(client, account);
		}
	} // end of getUserByName

	
	public boolean userEmailExists(String email) 
											throws IOException, SQLException 
	{
		// Get an autoclosable connection
		try (Connection con = DBConnectionUtil.getConnection()) {
			return userEmailExists(email, con);
		}
	} // end of userEmailExists
	
	public boolean userEmailExists(String email, Connection con) 
											throws SQLException 
	{
		ClientDao clientDao = new ClientDaoImpl();
		return clientDao.getClientByEmail(email, con) != null;
	} // end of userEmailExists
	

	@Override
	public BankUserData getUserByEmail(String email) 
											throws IOException, SQLException 
	{
		// Get an autoclosable connection
		try (Connection con = DBConnectionUtil.getConnection()) {
			// Get the information about the user from the server
			Client client = getClientByEmail(email, con);
			if (client == null) {
				return null;
			}
			
			Account account = getAccount(client, con);
			if (account == null) {
				return null;
			}
			
			return toBankUserData(client, account);
		}
	} // end of getUserByEmail
	
	
	// TODO: Test later behavior when adding users concurrently. I suspect 
	// TODO: this transaction setup might cause problems with conflicting 
	// TODO: generated Primary Keys. 
	// Throws an exception if problem, returns true if no problem
	@Override
	public boolean addNewUser(BankUserData newUser) 
											throws IOException, SQLException {
		// Setup the necessary DAOs
		ClientDao clientDao = new ClientDaoImpl();
		AccountDao accountDao = new AccountDaoImpl();
		ClientToAccountDao ctaDao = new ClientToAccountDaoImpl();
		
		//Get the client and account objects to save. 
		Client client = new Client();
		Account account = new Account();
		
		// Stores the client or account IDs that were generated on the server
		long generatedId = 0;
		
		// Initialize the client and account data based on newUser
		fromBankUserData(newUser, client, account);
		
		
		// If a client and an account were created, pull the client and account
		// from the server to get their generated id's
		try (Connection con = DBConnectionUtil.getConnection()) {
			// Turn off autocommit as we will need multiple operations to be 
			// atomic. 
			con.setAutoCommit(false);
			
			// Create a new client on the DB and get its generated Primary key. 
			generatedId = clientDao.createClient(client, con);
			client.setClientId(generatedId);
			
			// Create a new account on the DB and get its generated Primary key.
			generatedId = accountDao.createAccount(account, con);
			account.setAccId(generatedId);
			
			// Create a link between the new client and account
			ctaDao.createCLtoAC(client, account, con);
			
			// Commit the changes
			con.commit();
			
			return true;
		}
	} // end of addNewUser

	
	@Override
	public boolean withdraw(BankUserData user, BigDecimal amount) 
											throws IOException, SQLException 
	{
		try (Connection con = DBConnectionUtil.getConnection()) {
			// Get the information about the user from the server
			// Return false if there is no user by that email. 
			Client client = getClientByEmail(user.getEmail(), con);
			if (client == null) {
				log.info("Client '" + user.getEmail() + "' not found. ");
				return false;
			}
			
			// Get the information about the user from the server
			// Return false if the client has no accounts
			Account account = getAccount(client, con);
			if (account == null) {
				log.info("No account for Client '" 
										+ client.getEmail() + "' found. ");
				return false;
			}
			
			// If there isn't enough money. Don't do anything. 
			if (account.getBalance().compareTo(amount) < 0) {
				return false;
			}
			
			// Change he balance on the account object
			account.setBalance(account.getBalance().subtract(amount));
			
			// TODO: Implement higher level serialization that makes withdrawal
			// TODO: transactions on individual accounts completely serial. 
			// TODO: Otherwise, bad things happen if concurrent withdrawals are 
			// TODO: made. 
			// Update the balance on both the server and the client
			AccountDao accountDao = new AccountDaoImpl();
			accountDao.updateAccount(account, con);
			
			// Update the client side balance accordingly
			user.setBalance(account.getBalance());
			
			return true;
		}
	} // end of withdraw
	
	
	@Override
	public boolean deposit(BankUserData user, BigDecimal amount) throws SQLException, IOException {
		try (Connection con = DBConnectionUtil.getConnection()) {
			// Get the information about the user from the server
			// Return false if there is no user by that email. 
			Client client = getClientByEmail(user.getEmail(), con);
			if (client == null) {
				log.info("Client '" + user.getEmail() + "' not found. ");
				return false;
			}
			
			// Get the information about the user from the server
			// Return false if the client has no accounts
			Account account = getAccount(client, con);
			if (account == null) {
				log.info("No account for Client '" 
										+ client.getEmail() + "' found. ");
				return false;
			}
			
			// Change the balance on the account object
			account.setBalance(account.getBalance().add(amount));
			
			// TODO: Implement higher level serialization that makes deposit
			// TODO: transactions on individual accounts completely serial. 
			// TODO: Otherwise, bad things happen if concurrent deposits are 
			// TODO: made. 
			// Update the balance held on the server side
			AccountDao accountDao = new AccountDaoImpl();
			accountDao.updateAccount(account, con);
			
			// Update the balance held on the client side
			user.setBalance(account.getBalance());
			
			return true;
		}
	} // end of deposit
	
	
	
	
	
	/** Gets a client from the database based on the user's email. 
	 * Returns null if there isn't a user with that email.
	 * @throws SQLException */
	private Client getClientByEmail(String email, Connection con) 
														throws SQLException 
	{
		ClientDao clientDao = new ClientDaoImpl();
		return clientDao.getClientByEmail(email, con);
	} // end of getClientByEmail
	
	/** Gets a client from the database based on the user's username. 
	 * Returns null if there isn't a user with that username. */
	private Client getClientByUsername(String username, Connection con) 
														throws SQLException 
	{
		ClientDao clientDao = new ClientDaoImpl();
		return clientDao.getClientByUsername(username, con);
	} // end of getClientByUsername
	
	
	/** A temporary private helper method that gets an account from the 
	 * database for a specific bank client. Later, a client will have to access
	 * multiple accounts. */
	private Account getAccount(Client client, Connection con) 
														throws SQLException 
	{
		ClientToAccountDao ctaDao = new ClientToAccountDaoImpl();
		
		List<Account> accounts = ctaDao.getAccountsByClient(client, con);
		
		// If we have no accounts for this client, return null.
		if (accounts.isEmpty()) {
			return null;
		}
		
		// Otherwise, just return the first account, as clients will only have 
		// a single checking account at this point. 
		return accounts.get(0);
	} // end of getAccount
	
	
	/** Converts a client and an account into a single BankUserData object. 
	 * @return */
	private BankUserData toBankUserData(Client client, Account account) 
	{
		BankUserData userData = new BankUserData();
		userData.setUniqueID(client.getClientId());
		userData.setEmail(client.getEmail());
		userData.setUsername(client.getUsername());
		userData.setPassPhrase(client.getPassPhrase());
		userData.setBalance(account.getBalance());
		
		return userData;
	} // end of toBankUserData
	
	/** Given, a BankUserData object, sets the appropriate fields in the 
	 * provided Client and Account objects based on the BankUserData. */
	private void fromBankUserData(BankUserData userData,
									Client client, 
									Account account) 
	{
		client.setEmail(userData.getEmail());
		client.setPassPhrase(userData.getPassPhrase());
		client.setUsername(userData.getUsername());
		account.setBalance(userData.getBalance());
		// Set to the default type of account for this bridge
		account.setAccType(DEFAULT_ACCOUNT);
	} // end of fromBankUserData
	
}

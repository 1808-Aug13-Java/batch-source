package com.revature.client;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

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
	public boolean userNameExists(String userName) {
		ClientDao clientDao = new ClientDaoImpl();
		try {
			return clientDao.getClientByUsername(userName, DBConnectionUtil.getConnection()) != null;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public BankUserData getUserByName(String userName) {
		// Get the information about the user from the server
		Client client = getClientByUsername(userName);
		Account account = getAccount(client);
		
		return toBankUserData(client, account);
	}

	@Override
	public boolean userEmailExists(String email) {
		ClientDao clientDao = new ClientDaoImpl();
		try {
			return clientDao.getClientByEmail(email, DBConnectionUtil.getConnection()) != null;
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public BankUserData getUserByEmail(String email)  {
		// Get the information about the user from the server
		Client client = getClientByEmail(email);
		Account account = getAccount(client);
		
		return toBankUserData(client, account);
	}

	@Override
	public boolean addNewUser(BankUserData newUser) {
		// Setup the necessary DAOs
		ClientDao clientDao = new ClientDaoImpl();
		AccountDao accountDao = new AccountDaoImpl();
		ClientToAccountDao ctaDao = new ClientToAccountDaoImpl();
		
		//Get the client and account objects to save. 
		Client client = new Client();
		Account account = new Account();
		
		// Initialize the client and account data based on newUser
		fromBankUserData(newUser, client, account);
		
		//TODO: Implement a better server side ID generator. For now, this will 
		// do, even if it can cause errors. 
		Random rand = new Random();
		
		long clientId = rand.nextLong();
		long accId = rand.nextLong();
		client.setClientId(clientId);
		
		
		// If a client and an account were created, pull the client and account
		// from the server to get their generated id's
		try {
			// Set the account id 
			clientDao.createClient(client, DBConnectionUtil.getConnection());
			accountDao.createAccount(account, DBConnectionUtil.getConnection());
			return ctaDao.createCLtoAC(client, account, DBConnectionUtil.getConnection()) == 1;
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean withdraw(BankUserData user, BigDecimal amount) {
		// Get the information about the user from the server
		Client client = getClientByEmail(user.email);
		Account account = getAccount(client);
		
		// If there isn't enough money. Don't do anything. 
		if (account.getBalance().compareTo(amount) < 0) {
			return false;
		}
		log.info("Balance: " + account.getBalance() + "  amount: " + amount);
		
		account.setBalance(account.getBalance().subtract(amount));
		
		
		// Update the balance on both the server and the client
		AccountDao accountDao = new AccountDaoImpl();
		try {
			accountDao.updateAccount(account, DBConnectionUtil.getConnection());
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.balance = account.getBalance();
		
		return true;
	}

	@Override
	public void deposit(BankUserData user, BigDecimal amount) {
		// Get the information about the user from the server
		Client client = getClientByEmail(user.email);
		Account account = getAccount(client);
		
		// Update the balance on both the server and the client
		account.setBalance(account.getBalance().add(amount));
		user.balance = account.getBalance();
		
		AccountDao accountDao = new AccountDaoImpl();
		try {
			accountDao.updateAccount(account, DBConnectionUtil.getConnection());
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/** Gets a client from the database based on the user's email. */
	private Client getClientByEmail(String email) {
		ClientDao clientDao = new ClientDaoImpl();
		try {
			return clientDao.getClientByEmail(email, DBConnectionUtil.getConnection());
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/** Gets a client from the database based on the user's username. */
	private Client getClientByUsername(String username) {
		ClientDao clientDao = new ClientDaoImpl();
		try {
			return clientDao.getClientByUsername(username, DBConnectionUtil.getConnection());
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/** Gets an account from the database for a specific bank client. */
	private Account getAccount(Client client) {
		ClientToAccountDao ctaDao = new ClientToAccountDaoImpl();
		
		List<Account> accounts = null;
		try {
			accounts = ctaDao.getAccountsByClient(client, DBConnectionUtil.getConnection());
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// If we have no accounts for this client, return null.
		if (accounts.isEmpty()) {
			return null;
		}
		
		// Otherwise, just return the first account, as clients will only have 
		// a single checking account at this point. 
		return accounts.get(0);
	}
	
	
	/** Converts a client and an account into a single BankUserData object. 
	 * @return */
	private BankUserData toBankUserData(Client client, Account account) {
		BankUserData userData = new BankUserData();
		userData.uniqueID = client.getClientId();
		userData.email = client.getEmail();
		userData.userName = client.getUsername();
		userData.passwordHash = client.getPassPhrase();
		userData.balance = account.getBalance();
		
		return userData;
	}
	
	/** Given, a BankUserData object, sets the appropriate fields in the 
	 * provided Client and Account objects based on the BankUserData. */
	private void fromBankUserData(BankUserData userData,
									Client client, 
									Account account) 
	{
		client.setEmail(userData.email);
		client.setPassPhrase(userData.passwordHash);
		client.setUsername(userData.userName);
		account.setBalance(userData.balance);
		// Set to the default type of account for this bridge
		account.setAccType(DEFAULT_ACCOUNT);
	}
	
}

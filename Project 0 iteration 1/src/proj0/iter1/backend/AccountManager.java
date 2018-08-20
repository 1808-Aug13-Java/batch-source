package proj0.iter1.backend;

import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * class AccountManager
 * Responsible for managing interactions between the user and his/her account,
 * 		including managing log-ons and storage
 * This class uses nothing but static methods as instances are redundant
 * @author jljac
 *
 */
public class AccountManager 
{
	// Holds accounts currently active
	private static HashMap<Integer, UserAccount> activeAccounts = new HashMap<>();	
	
	/**
	 * saveAccount - Refractors the procedure to serialize an account
	 * @param ua - the User Account to save
	 * @return boolean - success or not
	 */
	protected static boolean saveAccount(UserAccount ua)
	{
		if(ua == null)
			return false;
		// Attempt to save and close the account
		try
		{
			// Set up output objects
			FileOutputStream fos = new FileOutputStream("./Accounts/" + ua.getUsername());
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			// Save the Account and close the files
			oos.writeObject(ua);
			oos.close();
			fos.close();
		} 
		catch (FileNotFoundException e)
		{
			return false;
		
		} 
		catch (IOException e) 
		{
			
			return false;
		}
		return true;
	}
	
	/**
	 * getAvailableToken - Checks the list of active accounts for a token
	 * @param ua - the User Account to add if available
	 * @return the token to use for the account's session
	 */
	protected static int getAvailableToken(UserAccount ua)
	{
		// Go through the list of possible positive integers for a space
		// Starting at 1 since 0 is used by accounts as no logged on
		for(int c = 1; c < Integer.MAX_VALUE; c++)
		{
			if(!activeAccounts.containsKey(c))
			{
				// Found a space, use it
				activeAccounts.put(c, ua);
				return c;
			}
		}
		
		// No space available, report the User Level value for not logged on
		return -1;
	}
	
	/**
	 * createAccount - creates an account and attempts to log into it,
	 * 		assuming the account does not currently exist
	 * @param fn - the user's first name
	 * @param ln - the user's last name
	 * @param un - the user's username (used to store account in file)
	 * @param em - the user's email
	 * @param pw - the User's password (should be encrypted, but will be added before release)
	 * @return the token of the current session (-1 if failed)
	 */
	public static int createAccount(String fn, String ln, String un, String em, String pw)
	{
		// can't create an account if it already exists
		try
		{
			// For once, we actually want an exception.
			FileInputStream fis = new FileInputStream("./Accounts/" + un);
			
			// If we reach this point, the account exists and we do not wish to override it
			fis.close();
			return -1;
		}
		catch(IOException e)
		{
			
		}
		
		// We are in the clear and can proceed with account creation
		UserAccount ua = new UserAccount(fn, ln, un, em, pw);
		return ua.logInAccount(un, pw);
		 
	}
	
	/**
	 * logIn - logs someone in to their current account, if it exists
	 * @param id - the username
	 * @param pw - the password
	 * @return int - the token to use (-1 if authentication failed, -2 or -4 if account doesn't exist)
	 */
	public static int logIn(String id, String pw)
	{
		// Create the two input objects we need to recreate the account
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			// File should be stored in the "accounts" folder
			fis = new FileInputStream("./Accounts/" + id);
		
			ois  = new ObjectInputStream(fis);
			
			// Set up a token to return to the user
			int tok = 0;
		
			UserAccount ua = (UserAccount) ois.readObject();
			if((tok = ua.logInAccount(id, pw)) < 1) 
			{
				// Here, logging in failed, likely due to invalid credentials
				ois.close();
				fis.close();
				return -1; // return the not logged token
			}
			// Here, we can return an active token
			ois.close();
			fis.close();
			
			saveAccount(ua);
			
			return tok;
		}
		// Remaining code deals with potential errors related to IO
		catch (ClassNotFoundException e)
		{
			try
			{
				fis.close();
				ois.close();
			}
			catch (IOException e1) { return -4; }
		}
		catch(IOException e) {
			return -4;
		}
		
		return -2;
	}
	
	/**
	 * logOut - logs a user out of an account, saves the account to disk, and removes it from memory
	 * @param token - the token of the current user
	 * @return boolean - true if log-out was successful. false if an error (not logged on or IO) occurs 
	 */
	public static boolean logOut(int token)
	{
		// Retrieve the account to operate on
		UserAccount ua = activeAccounts.get(token);
		
		// If null, the account is not currently logged on and return false
		if(ua == null)
			return false;
		
		// Attempt to save and close the account
		boolean ret = saveAccount(ua);
		
		// Remove account from active accounts
		activeAccounts.remove(token);
		return ret;
	}
	
	/**
	 * withdrawl - with drawls money from an active account
	 * @param token - the account to use
	 * @param dollars - the amount of dollars to withdraw
	 * @param cents - the amount of cents to withdraw
	 * @return - -1 if account isn't active, 1 if account doesn't have the funds, 0 for success
	 */
	public static int withdrawl(int token, int dollars, byte cents)
	{
		// First get the account and check for validity
		UserAccount ua = activeAccounts.get(token);
		if(ua == null)
			return -1;
		
		// Attempt a withdraw and report the success or failure of the transaction
		if(ua.withdrawl(dollars, cents))
		{
			// Go ahead and save the account
			saveAccount(ua);
			return 0;
		}
		return 1;
	}
	
	/**
	 * deposit - deposits money into a logged in account
	 * @param token - the account that should be active
	 * @param dollars - Dollars to add
	 * @param cents - cents to add
	 * @return false if any parameter is invalid (account not logged on or money is negative)
	 */
	public static boolean deposit(int token, int dollars, byte cents)
	{
		// Check for the account
		UserAccount ua = activeAccounts.get(token);
		if(ua == null)
			return false;
		
		// Make sure money is being "added", not subtracted
		if(dollars < 0 || cents < 0)
			return false;
		
		// Add the money
		dollars += (cents / 100);
		cents = (byte) (cents % 100);
		ua.deposit(dollars, cents);
		
		saveAccount(ua);
		
		return true;
	}

	/**
	 * getAccountInformation - reports the basic details for the account
	 * @param token - the account to check
	 * @return - the message on the account
	 */
	public static String getAccountInformation(int token)
	{
		UserAccount ua = activeAccounts.get(token);
		if(ua == null)
			return "Sorry, you are not properly logged in!";
		
		return "[" + ua.getName() + " : " + ua.getBalance() + "]";
	}
}

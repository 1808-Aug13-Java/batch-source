package proj0.iter1.backend;

import java.io.Serializable;

/**
 * class UserAccount - represents accounts in the system
 * implements Serializable - allows class to be saved
 * @author jljac
 *
 */
public class UserAccount implements Serializable
{
	
	private static final long serialVersionUID = -3913198663208232531L;
	
	// Details of the account
	private String firstName, lastName, userName, email;
	private String password;
	
	private int dollars;
	private byte cents;
	
	/**
	 * UserAccount - sets up the user account
	 * @param fn - first name
	 * @param ln - last name
	 * @param un - username
	 * @param email - email to use
	 * @param pw - password to use
	 */
	protected UserAccount(String fn, String ln, String un, String email, String pw)
	{
		firstName = fn;
		lastName = ln;
		userName = un;
		this.email = email;
		password = pw;
		dollars = 0;
		cents = 0;
		
	}
	
	// This is meant to be part of the login session, not the actual account
	transient int token = 0;
	
	/**
	 * logInAccount - attempts to log into the system, assuming credentials are valid
	 * @param id - Username
	 * @param password - the password to use
	 * @return int - token if above 0, -1 if already logged on, -4 if authentication failed
	 */
	int logInAccount(String id, String password)
	{
		// If above 0, Account is already active so don't log on again
		if(token > 0)
			return -1;
		
		// Check for validity
		if(password.equals(this.password) && (id.equals(userName) || id.equals(email)))
		{
			// Get registered in the system and get the token to prove it
			token = AccountManager.getAvailableToken(this);
			return token;
		}
		// Failed, authentication failed
		return -4;
	}
	
	/**
	 * withdrawl - reduces the money by the specified amount, if available
	 * @param dollars - dollars to reduce by
	 * @param cents - cents to reduce by
	 * @return boolean- true if successful, false, if bounce will occur
	 */
	boolean withdrawl(int dollars, byte cents)
	{
		// Create temp variables to catch failure before it affects account
		int tempDol = this.dollars;
		byte tempCent = this.cents;
		
		// Invalid value
		if(cents > 99)
			return false;
		if(cents > 0)
		{
			if(tempCent > cents)
			{
				tempCent -= cents;
			}
			else if(tempCent == cents)
			{
				tempCent = 0;
			}
			else
			{
				tempDol--;
				if(tempDol < 0)
					return false;
				tempCent += 100;
				tempCent -= cents;
			}
		}
		
		if(dollars > 0)
		{
			if(tempDol < dollars)
				return false;
			tempDol -= dollars;
		}
		
		this.dollars = tempDol;
		this.cents = tempCent;
		return true;
	}
	
	/**
	 * deposit - adds money to account
	 * @param dollars - dollars to add
	 * @param cents - cents to add
	 */
	protected void deposit(int dollars, byte cents)
	{
		// do a basic add
		this.dollars += dollars;
		this.cents += cents;
		
		// Reset for possible overflow in the cents category
		this.dollars += this.cents / 100;
		this.cents = (byte)(this.cents % 100);
	}
	
	/**
	 * getBalance - retrieves the string form of the balance
	 * @return the string form of the balance
	 */
	protected String getBalance()
	{
		String ret = "$" + dollars + ".";
		// Address single cent values
		if(cents < 10)
			ret += "0";
		ret += cents;
		return ret;
	}
	
	/**
	 * getName - Retrieves the name of the account
	 * @return - the first and last name
	 */
	protected String getName()
	{
		return firstName + " " + lastName;
	}
	
	/**
	 * getUsername - retrieves the username of the account
	 * @return String - the username
	 */
	protected String getUsername()
	{
		return userName;
	}
	
	/**
	 * getToken - retrieves the token of the account
	 * @return int - account token in use
	 */
	protected int getToken()
	{
		return token;
	}
}

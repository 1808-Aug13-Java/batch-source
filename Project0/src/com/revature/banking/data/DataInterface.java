package com.revature.banking.data;

import java.math.BigDecimal;

/** 
 * This interface serves as the structure for an api between the 
 * back end data layer  and the middle service layer. Any classes that 
 * implement this will be able to interface with the banking app. 
 */
public interface DataInterface {
	
	//TODO: Consider throwing exception through this interface in case 
	// of data reading failure. 
	
	// TODO: Refine interface more tomorrow, as it might serve the basis for 
	// the transfer of data. MAybe consider detaching the interface and making 
	// a more general api that's more flexible later. 
	
	/**
	 * Queries the database to see if the specified user exists. 
	 * @return True or False depending on if the user exists
	 */
	public boolean userNameExists(String userName);
	
	/** 
	 * Gets a user by name the user name. Returns null if the specified 
	 * user does not exist in the database, or the connection failed. 
	 */
	public BankUserData getUserByName(String userName);
	
	/**
	 * Queries the database to see if the specified email exists. 
	 * @return True or False depending on if the email exists
	 */
	public boolean userEmailExists(String email);
	
	/** 
	 * Gets a user by email the user name. Returns null if the specified 
	 * email does not exist in the database, or the connection failed. 
	 */
	public BankUserData getUserByEmail(String email);
	
	/**
	 * Attempts to add a new user to the database. It might fail due to 
	 * the user already existing, or for connection reasons. 
	 * @param newUser - The user to add to the database 
	 * @return True if the user was successfully added. False otherwise. 
	 */
	public boolean addNewUser(BankUserData newUser);
	
	
	public boolean withdraw(BankUserData user, BigDecimal amount);
	
	
	public boolean deposit(BankUserData user, BigDecimal amount);
	
	
	
}

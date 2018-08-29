package com.revature.client.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

/** 
 * This interface serves as the structure for an api between the 
 * back end data layer  and the middle service layer. Any classes that 
 * implement this will be able to interface with the banking app. 
 */
public interface DataInterface {
	
	
	/**
	 * Queries the database to see if the specified user exists. 
	 * @return True or False depending on if the user exists
	 */
	public boolean userNameExists(String userName) 
											throws IOException, SQLException;
	
	/** 
	 * Gets a user by name the user name. Returns null if the specified 
	 * user does not exist in the database, or the connection failed. 
	 */
	public BankUserData getUserByName(String userName) 
											throws IOException, SQLException;
	
	/**
	 * Queries the database to see if the specified email exists. 
	 * @return True or False depending on if the email exists
	 */
	public boolean userEmailExists(String email) 
											throws IOException, SQLException;
	
	/** 
	 * Gets a user by email the user name. Returns null if the specified 
	 * email does not exist in the database, or the connection failed. 
	 */
	public BankUserData getUserByEmail(String email) 
											throws IOException, SQLException;
	
	/**
	 * Attempts to add a new user to the database. It might fail due to 
	 * the user already existing, or for connection reasons. 
	 * @param newUser - The user to add to the database 
	 * @return True if the user was successfully added. False otherwise. 
	 */
	public boolean addNewUser(BankUserData newUser) 
											throws IOException, SQLException;
	
	
	/** 
	 * Withdraws the specified amount from the specified user's account. 
	 * @param user - The user's account to perform this transaction on
	 * @param amount - The amount of the transaction
	 * @return True if the operation succeeded, false if there wasn't 
	 * enough funds to withdraw. 
	 */
	public boolean withdraw(BankUserData user, BigDecimal amount) 
											throws IOException, SQLException;
	
	/** 
	 * Deposits the specified amount into the specified user's account. 
	 * @param user - The user's account to perform this transaction on
	 * @param amount - The amount of the transaction
	 * @return
	 */
	public boolean deposit(BankUserData user, BigDecimal amount) 
											throws IOException, SQLException;
	
	
	
}

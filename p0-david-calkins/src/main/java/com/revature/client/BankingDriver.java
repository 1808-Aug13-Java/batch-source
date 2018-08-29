package com.revature.client;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.client.data.BankUserData;

/** A command line interface to a banking app. */
public class BankingDriver {
	
	/** The default logging object. */
	private static Logger log = Logger.getRootLogger();
	
	/** An enum used to control the flow of a state machine for the terminal */
	private enum StateEnum {
		/** Represents the state of the login menu. */
		STATE_LOGIN_MENU,
		/** Represents the state of when the user is attempting to log in. */
		STATE_LOGIN,
		/** Represents the state of creating a user. */
		STATE_CREATE_USER,
		/** Represents the state of the main user menu, once logged in. */
		STATE_USER_MENU,
	}
	
	
	public static void main(String[] args) {
		// A scanner to take user input from
		Scanner scanner = new Scanner(System.in);
		
		// The back end of the bank. All the data is accessed through this 
		// object. 
		BankServerBridge bankDatabase = new BankServerBridge();
		
		// A boolean that specifies when the user wants to quit the terminal
		boolean quit = false;
		
		// An enum that represents the state of this state machine terminal
		// Default to the login screen
		StateEnum currentState = StateEnum.STATE_LOGIN_MENU;
		
		// A string used to hold input from the user. 
		String userInput = null;
		
		// A string array for when the user enters multiple tokens separated by 
		// spaces. 
		String[] tokens = null;
		
		// An object for holding information about a bank user
		BankUserData bankUserData = null;
		BankUserData tempUserData = null;
		
		// Used to hold user input amounts as they are being verified
		BigDecimal inputAmount = null;
		
		// Surround the whole terminal loop with a try/catch tat traps 
		// for IOException, as an IOException would be the result of not 
		// properly loading the connection details. If we can't make a 
		// connection, there is nothing to do. 
		try {
			// Loop until the user decides to quit the terminal
			while (!quit) {
				// If there is an SQLException, instead of quitting, return to 
				// the login menu as the problem could be handled. 
				try {
					// Go into an accepting state at the login screen, which has options
					// to either create a user or to log in. 
					while (currentState == StateEnum.STATE_LOGIN_MENU && !quit) {
						log.info("Welcome to the Bank!");
						log.info("Enter the number of the option you want ");
						log.info("on the console, and then press enter. ");
						log.info("1. Log In");
						log.info("2. Create New User");
						log.info("3. Quit Terminal");
						
						// Get input from the user
						userInput = scanner.nextLine();
						
						// Select a new menu based on the users selection. 
						switch(userInput) {
						case "1": currentState = StateEnum.STATE_LOGIN; break;
						case "2": currentState = StateEnum.STATE_CREATE_USER; break;
						case "3": quit = true; break;
						default: log.info("'" + userInput + "' isn't valid. ");
						}
					} // end while
					
					// Go into an accepting state for the user's email and password to
					// make a new account
					while (currentState == StateEnum.STATE_CREATE_USER) {
						log.info("Pleae enter an email, a username, and a new password "
								+ "separated by a space. ");
						log.info("Enter 'Back' to go back to the login menu");
						
						
						userInput = scanner.nextLine();
						tokens = userInput.split(" ");
						
						// If the user has entered 'back' take them to the previous menu
						if (tokens[0].equalsIgnoreCase("back")) {
							currentState = StateEnum.STATE_LOGIN_MENU;
						}
						// Otherwise, check and validate the email, username, and password
						// In this version of the app, actual email format validation 
						// doesn't exist. Neither does password hashing. 
						else if (tokens.length == 3)
						{
							// If the user email doesn't exist in the database, add 
							// a new user, with the email, username, and password.
							if (!bankDatabase.userEmailExists(tokens[0])) {
								bankUserData = new BankUserData();
								bankUserData.setEmail(tokens[0]);
								bankUserData.setUsername(tokens[1]);
								bankUserData.setPassPhrase(tokens[2]);
								
								bankDatabase.addNewUser(bankUserData);
								log.info("User Created");
								currentState = StateEnum.STATE_LOGIN_MENU;
								
								// Remove reference to new user
								bankUserData = null;
							}
							// Otherwise, the user exits. 
							else {
								log.info("That email already exists. ");
							}
						}
						else {
							log.info("'" + userInput + "' isn't valid. ");
						}
					} // end while
					
					
					// Go into an accepting state for the user's email and password to
					// log in
					while (currentState == StateEnum.STATE_LOGIN) {
						log.info("Pleae enter your email and password separated by "
								+ "a space. ");
						log.info("Enter 'Back' to go back to the login menu");
						
						
						userInput = scanner.nextLine();
						tokens = userInput.split(" ");
						
						// If the user has entered 'back' take them to the previous menu
						if (tokens[0].equalsIgnoreCase("back")) {
							currentState = StateEnum.STATE_LOGIN_MENU;
						}
						// Otherwise, check and validate the email and password
						else if (tokens.length == 2)
						{
							// If the user email exists, and the password is correct, 
							// let the user in
							if ((bankUserData = bankDatabase.getUserByEmail(tokens[0])) != null
									&& bankUserData.getPassPhrase().equals(tokens[1]))
							{
								log.info("Login Successful");
								currentState = StateEnum.STATE_USER_MENU;
							}
							// Otherwise, the the user login credentials are invalid
							else {
								log.info("No Username / Password match found");
							}
						}
						else {
							log.info("'" + userInput + "' isn't valid. ");
						}
					} // end while
					
					
					// Go into the accepting state for a logged in user
					while (currentState == StateEnum.STATE_USER_MENU) {
						log.info("Welcome " + bankUserData.getUsername() + "!");
						log.info("Type 'deposit <amount>' to deposit. ");
						log.info("Type 'withdraw <amount>' to withdraw. ");
						log.info("Type 'balance' to view your current balance");
						log.info("Type 'logout' to log out. ");
						
						
						userInput = scanner.nextLine();
						tokens = userInput.split(" ");
						
						
						// Switch on the first token. 
						switch(tokens[0]) {
						// If depositing, parse the amount into a BigDecimal, and 
						// deposit
						case "deposit": 
							// If the amount isn't specified, specify it
							if (tokens.length < 2) {
								log.info("Please specify <amount>");
								continue;
							}
							
							// Validate that it is a valid number before depositing
							try {
								// Attempt to convert the string into a BigDecimal. 
								// Throws NumberFormatException if not a number
								inputAmount = new BigDecimal(tokens[1]);
								
								// If the number is not positive, don't deposit
								if (inputAmount.signum() != 1) {
									log.info("Deposits must be positive. ");
									break;
								}
								bankDatabase.deposit(bankUserData, inputAmount);
								log.info("Deposit Successful. ");
							} catch (NumberFormatException ex) {
								log.info("'" + tokens[1] + "' isn't a number");
							}
							break;
						case "withdraw":
							// If the amount isn't specified, specify it
							if (tokens.length < 2) {
								log.info("Please specify <amount>");
								continue;
							}
							
							// Validate that it is a valid number before withdrawing
							try {
								// Attempt to convert the string into a BigDecimal. 
								// Throws NumberFormatException if not a number
								inputAmount = new BigDecimal(tokens[1]);
								
								// If the number is not positive, don't withdraw
								if (inputAmount.signum() != 1) {
									log.info("Withdrawls must be positive. ");
									break;
								}
								// Also validate that there is enough in the account 
								if (bankDatabase.withdraw(bankUserData, inputAmount)) 
								{
									log.info("Withdraw Successful. ");
								}
								// Otherwise, print that there wasn't enough
								else {
									log.info("Not Enough Funds In Account");
								}
							} catch (NumberFormatException ex) {
								log.info("'" + tokens[1] + "' isn't a number");
							}
							break;
						case "balance": 
							// Get the latest data from the server
							tempUserData = bankDatabase.getUserByEmail(bankUserData.getEmail());
							
							if (tempUserData == null) {
								log.error("User not found: " + bankUserData.getEmail());
								break;
							}
							log.info("Balance: " + tempUserData.getBalance());
							break;
						case "logout":
							log.info("Logged Out");
							currentState = StateEnum.STATE_LOGIN_MENU;
							break;
						default:
							log.info("'" + userInput + "' isn't valid. ");
						} // end switch
					} // end while
				
				} catch (SQLException e) {
					// If there is a problem, notify user and return to login 
					// menu. 
					log.error("There was a problem: " + e);
					currentState = StateEnum.STATE_LOGIN_MENU;
				}
			} // end while
		
		} catch(IOException e) {
			log.error("The configuration details couldn't be loaded: " + e);
		}
		
		
		
		log.info("Thank you! Come again!");
		
		
		// Close the scanner 
		scanner.close();
		
	} // end of main
	
} // end of class BankingDriver

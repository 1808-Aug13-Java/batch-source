package com.revature.client;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import com.revature.client.data.BankUserData;
import com.revature.client.data.DataInterface;

/** A command line interface to a banking app. */
public class BankingDriver {
	
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
		
		// Used to hold user input amounts as they are being verified
		BigDecimal inputAmount = null;
		
		// A zero constant used to make comparisons against. 
		final BigDecimal ZERO = new BigDecimal(0);
		
		
		
		// Loop until the user decides to quit the terminal
		while (!quit) {
			
			// Go into an accepting state at the login screen, which has options
			// to either create a user or to log in. 
			while (currentState == StateEnum.STATE_LOGIN_MENU && !quit) {
				System.out.println("Welcome to the Bank!");
				System.out.println("Enter the number of the option you want ");
				System.out.println("on the console, and then press enter. ");
				System.out.println("1. Log In");
				System.out.println("2. Create New User");
				System.out.println("3. Quit Terminal");
				
				// Get input from the user
				userInput = scanner.nextLine();
				
				// Select a new menu based on the users selection. 
				switch(userInput) {
				case "1": currentState = StateEnum.STATE_LOGIN; break;
				case "2": currentState = StateEnum.STATE_CREATE_USER; break;
				case "3": quit = true; break;
				default: System.out.println("'" + userInput + "' isn't valid. ");
				}
			} // end while
			
			
			
			
			// Go into an accepting state for the user's email and password to
			// make a new account
			while (currentState == StateEnum.STATE_CREATE_USER) {
				System.out.println(
						"Pleae enter an email, a username, and a new password "
						+ "separated by a space. Enter 'Back' to go back to "
						+ "the login menu");
				
				
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
						bankUserData.email = tokens[0];
						bankUserData.userName = tokens[1];
						bankUserData.passwordHash = tokens[2];
						
						bankDatabase.addNewUser(bankUserData);
						System.out.println("User Created");
						currentState = StateEnum.STATE_LOGIN_MENU;
						
						// Remove reference to new user
						bankUserData = null;
					}
					// Otherwise, the user exits. 
					else {
						System.out.println("That email already exists. ");
					}
				}
				else {
					System.out.println("'" + userInput + "' isn't valid. ");
				}
			} // end while
			
			
			// Go into an accepting state for the user's email and password to
			// log in
			while (currentState == StateEnum.STATE_LOGIN) {
				System.out.println(
						"Pleae enter your email and password separated by "
						+ "a space. Enter 'Back' to go back to the login menu");
				
				
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
							&& bankUserData.passwordHash.equals(tokens[1]))
					{
						System.out.println("Login Successful");
						currentState = StateEnum.STATE_USER_MENU;
					}
					// Otherwise, the the user login credentials are invalid
					else {
						System.out.println("No Username / Password match found");
					}
				}
				else {
					System.out.println("'" + userInput + "' isn't valid. ");
				}
			} // end while
			
			
			// Go into the accepting state for a logged in user
			while (currentState == StateEnum.STATE_USER_MENU) {
				System.out.println("Welcome " + bankUserData.userName + "!");
				System.out.println("Type 'deposit <amount>' to deposit. ");
				System.out.println("Type 'withdraw <amount>' to withdraw. ");
				System.out.println("Type 'balance' to view your current balance");
				System.out.println("Type 'logout' to log out. ");
				
				
				userInput = scanner.nextLine();
				tokens = userInput.split(" ");
				
				
				// Switch on the first token. 
				switch(tokens[0]) {
				// If depositing, parse the amount into a BigDecimal, and 
				// deposit
				case "deposit": 
					// If the amount isn't specified, specify it
					if (tokens.length < 2) {
						System.out.println("Please specify <amount>");
						continue;
					}
					
					// Validate that it is a valid number before depositing
					try {
						// Attempt to convert the string into a BigDecimal. 
						// Throws NumberFormatException if not a number
						inputAmount = new BigDecimal(tokens[1]);
						
						// If the number is not positive, don't deposit
						if (inputAmount.signum() != 1) {
							System.out.println("Deposits must be positive. ");
							break;
						}
						bankDatabase.deposit(bankUserData, inputAmount);
						System.out.println("Deposit Successful. ");
					} catch (NumberFormatException ex) {
						System.out.println("'" + tokens[1] + "' isn't a number");
					}
					break;
				case "withdraw":
					// If the amount isn't specified, specify it
					if (tokens.length < 2) {
						System.out.println("Please specify <amount>");
						continue;
					}
					
					// Validate that it is a valid number before withdrawing
					try {
						// Attempt to convert the string into a BigDecimal. 
						// Throws NumberFormatException if not a number
						inputAmount = new BigDecimal(tokens[1]);
						
						// If the number is not positive, don't withdraw
						if (inputAmount.signum() != 1) {
							System.out.println("Withdrawls must be positive. ");
							break;
						}
						// Also validate that there is enough in the account 
						if (bankDatabase.withdraw(bankUserData, inputAmount)) 
						{
							System.out.println("Withdraw Successful. ");
						}
						// Otherwise, print that there wasn't enough
						else {
							System.out.println("Not Enough Funds In Account");
						}
					} catch (NumberFormatException ex) {
						System.out.println("'" + tokens[1] + "' isn't a number");
					}
					break;
				case "balance": 
					// Get the latest data from the server
					bankUserData = bankDatabase.getUserByEmail(bankUserData.email);
					
					System.out.println("Balance: " + bankUserData.balance);
					break;
				case "logout":
					System.out.println("Logged Out");
					currentState = StateEnum.STATE_LOGIN_MENU;
					break;
				default:
					System.out.println("'" + userInput + "' isn't valid. ");
				} // end switch
				
				// Set inputAmount to null to free up any memory used. 
				inputAmount = null;
			} // end while
			
			
			// Move to a new line every state change
			System.out.println();
		} // end while
		
		
		
		
		System.out.println("Thank you! Come again!");
		
	} // end of main
	
} // end of class BankingDriver

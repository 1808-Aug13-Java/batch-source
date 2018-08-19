package com.revature.banking;

import java.io.IOException;
import java.util.Scanner;

import com.revature.banking.data.BankUserData;
import com.revature.banking.data.FileBasedDatabase;

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
		FileBasedDatabase bankDatabase = new FileBasedDatabase();
		
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
		
		
		// Initialize the connection to the "database". If the "database" 
		// doesn't exist, just leave it in the default empty state. If there 
		// is an error loading it, quit as we can't do banking without a bank. 
		if (bankDatabase.dataFileExists()) {
			try {
				// Attempt to load the data from the file
				bankDatabase.loadInitialData();
			} 
			catch(IOException 
					| ClassCastException 
					| ClassNotFoundException e) 
			{
				System.err.println(
						"There was a problem loading the bank data: " + e);
				// If we can't "connect" to the bank, we can't do any banking. 
				return;
			}
		}
		else {
			System.out.println("No Bank File Found. Will create new one upon "
						+ "exiting Terminal. ");
		}
		
		
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
						"Pleae enter an email and a new password separated by "
						+ "a space. Enter 'Back' to go back to the login menu");
				
				
				userInput = scanner.nextLine();
				tokens = userInput.split(" ");
				
				// If the user has entered 'back' take them to the previous menu
				if (tokens[0].equalsIgnoreCase("back")) {
					currentState = StateEnum.STATE_LOGIN_MENU;
				}
				// Otherwise, check and validate the email and password
				// In this version of the app, actual email format validation 
				// doesn't exist. Neither does password hashing. 
				else if (tokens.length == 2)
				{
					// If the user email doesn't exist in the database, add 
					// a new user. 
					if (!bankDatabase.userEmailExists(tokens[0])) {
						bankUserData = new BankUserData();
						bankUserData.email = tokens[0];
						bankUserData.passwordHash = tokens[1];
						
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
			
			
			
			// Move to a new line every state change
			System.out.println();
		} // end while
		
		
		
		
		// Save the information to the local file system before exiting. 
		try {
			bankDatabase.saveData();
		} catch (IOException e) {
			System.err.println("There was a problem saving the bank data. ");
		}
		
		System.out.println("Thank you! Come again!");
		
	} // end of main
	
} // end of class BankingDriver

package com.revature.main;

import java.util.Scanner;


import org.apache.log4j.Logger;

import com.revature.dao.BankDaoImpl;
import com.revature.dao.BankUsersDaoImpl;
import com.revature.model.BankUsers;
import com.revature.util.UserInputValidation;

import com.revature.model.Bank;

public class BankCore {
	


	static final Logger logger = Logger.getLogger(BankCore.class);
	
	// set null variable for Singleton Design Pattern function
	private static BankCore singleton = null;
	private BankUsers userIsActive = null;

	public static final Scanner sc = new Scanner(System.in);

	public BankCore() {
		super();
	}
	
	
	
	public static BankCore setSingleton() {
		if (singleton == null) {
			singleton = new BankCore();
		}
		
		return singleton;
	}
	
	private void introMenu() {
		BankUsersDaoImpl buImp = new BankUsersDaoImpl();
		if(userIsActive == null) {
			logger.info("1. Create an account");
			logger.info("2. Sign In");
			logger.info("Enter q to quit");
		} else if (buImp.checkIfAccountExists(userIsActive.getUsername())) {
			logger.info("1. New Account Registration");
			logger.info("2. Deposit");
			logger.info("3. Withdraw");
			logger.info("4. Check balance");
			logger.info("Press q to quit");
		} else {
			logger.info("1. Create an account");
			logger.info("2. Logout");
			logger.info("Press q to quit");
		}
		
		logger.info("");
		
	}
	
	public void start() {
		logger.info("Welcome to Revature Bank!");
		introMenu();
		String userInput = "";
		
		while(userInput != "q") {
			
			userInput = sc.nextLine().toLowerCase();
			actOnInput(userInput);
			introMenu();

		}
		
	}
	
	// registers account
	public void createAccount() {
		BankUsersDaoImpl buImp = new BankUsersDaoImpl();
		BankUsers newUser = new BankUsers();
		String username = UserInputValidation.getValidUsername();
		newUser.setUsername(username);
		
		if (!buImp.isUsernameUnique(newUser)) {
			logger.info("Username already exists. Please try a different username.");
			return;
		}
		
		String email = UserInputValidation.getValidEmailAddress();
		newUser.setEmail(email);
		
		if (!buImp.isUserEmailUnique(newUser)) {
			logger.info("Email already exists in our database. Please try a different one.");
			return;
		}
		
		String password = UserInputValidation.getValidPassword();
		newUser.setUserPassword(password);
		
		buImp.createUser(newUser);
	}
	
	// creates the financial aspect of the user account
	public void createBank() {
		
		if (userIsActive == null)  {
			logger.info("Access denied.");
			return;
		}
		
		BankUsersDaoImpl usersDaoImpl =  new BankUsersDaoImpl();
		BankDaoImpl bankImpl = new BankDaoImpl();
		bankImpl.createBank(usersDaoImpl.getUserIdByUsername(userIsActive.getUsername()));

	}
	
	public void loggedOutOptions(String menuChoice) {
		
		switch(menuChoice) {
		case "1":
			createAccount();
			break;
		// add other cases after methods are established
		case "2":
			signIn();
			break;
		case "q":
			end();
			System.exit(0);
		default:
			break;
		}
		
	}
	
	public void actOnInput(String action) {
		if (userIsActive == null) {
			loggedOutOptions(action);
		} else {
			loggedInOptions(action);
		}
	}
	
	public void loggedInOptions(String action) {
		BankUsersDaoImpl userImpl = new BankUsersDaoImpl();
		
		switch(action) {
		
		case "1":
			createBank();
			break;
			
		case "2":
			if (!userImpl.checkIfAccountExists(userIsActive.getUsername())) {
				logger.info("Account does not exist.");
				break;
			}
			
			deposit();
			break;
			
		case "3":
			if(!userImpl.checkIfAccountExists(userIsActive.getUsername())) {
				logger.info("Account does not exist.");
				break;
			}
			
			withdraw();
			break;
		
		case "4":
			if(!userImpl.checkIfAccountExists(userIsActive.getUsername())) {
				logger.info("Account does not exist.");
				break;
			}
			
			checkBalance();
			break;
		case "q":
			end();
			System.exit(0);
		default:
			break;
			
		}
	}
	
	public void deposit() {
		BankDaoImpl bankImpl = new BankDaoImpl();
		Bank bank = bankImpl.getBankByUserId(userIsActive.getId());
		bankImpl.deposit(bank.getId());
		
	}
	
	public void signIn() {
		BankUsersDaoImpl bankUsersImpl = new BankUsersDaoImpl();
		logger.info("Please enter your username: ");
		String name = sc.nextLine();
		BankUsers user = bankUsersImpl.getUserByName(name);
		
		if (user == null) {
			logger.info("Invalid username. Please try again.");
			return;
		}
		
		logger.info("\n");
		logger.info(user.getUsername() + ", please enter your password: ");
		String pw = sc.nextLine();
		
		if (user.getUserPassword().equals(pw)) {
			// if the input matches the password of the user, change userIsActive from NULL to the pw
			userIsActive = user;
		} else {
			logger.info("Invalid password.");
		}
	}
	
	public void signOut() {
		userIsActive = null;
		logger.info("You have successfully logged out.");
	}
	
	private void checkBalance() {
		BankDaoImpl bankImpl = new BankDaoImpl();
		logger.info("Your balance is: $" + UserInputValidation.floatConfig(bankImpl.viewBalanceByUserId(userIsActive.getId())));
	}
	
	private void withdraw() {
		BankDaoImpl bankImpl = new BankDaoImpl();
		Bank bank = bankImpl.getBankByUserId(userIsActive.getId());
		bankImpl.withdraw(bank.getId());	
	}
	
	private void end() {
		logger.info("See you later!");
		userIsActive = null;
	}
	
	
}

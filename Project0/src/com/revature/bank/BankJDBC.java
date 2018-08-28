package com.revature.bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.BalanceDao;
import com.revature.dao.BalanceDaoImpl;
import com.revature.dao.LoginDao;
import com.revature.dao.LoginDaoImpl;
import com.revature.model.Balance;
import com.revature.model.Login;

public class BankJDBC {
	private static Logger log = Logger.getRootLogger();

	private Map<String, String> accounts = new HashMap<>();
	private Map<String, Integer> balances = new HashMap<>();
	private Map<String, BigDecimal> balancesBig = new HashMap<>();
	private static Scanner sc = new Scanner(System.in);
	private String user;
	private static String pathLogin = "src/com/revature/bank/login.txt";
	private static String pathBalances = "src/com/revature/bank/balances.txt";
	private BufferedWriter bw;
	
	public BankJDBC() {
		super();
		log.info("Initializing...");
		// Clear out storage if bank constructor is called again somehow
		accounts.clear();
		balances.clear();
		
		// Get login credentials from DB
		LoginDao ldi = new LoginDaoImpl();
		List<Login> loginList = ldi.getLogins();
		log.info("Loading Users...");
		
		// Populate accounts Map
		for (Login i : loginList) {
			accounts.put(i.getUsername(), i.getPassword());
		}
		
		log.info("Size of Accounts: " + accounts.size());
		
		// Get Balance information from DB
		BalanceDao bdi = new BalanceDaoImpl();
		List<Balance> balanceList = bdi.getBalances();
		log.info("Loading Balances...");
		
		// Populate balances map
		for (Balance b : balanceList) {
			balancesBig.put(b.getUsername(), b.getMoney());
		}
		
		log.info("Size of Balances: " + balancesBig.size());
	}
	
	public int menu() {
		log.info("\nPlease select an option\n"
						+ "1. Login\n"
						+ "2. Create New Account\n"
						+ "3. Exit");
		String input = sc.nextLine();
		if ( (input.length() != 1) || (!Character.isDigit(input.charAt(0))) || (Integer.valueOf(input) > 3)) {
			log.info("Error: Please enter a valid option");
			return menu();
		}
		return Integer.valueOf(input);
	}
	
	public boolean login() {
		boolean invalid = true;
		String pass = "";
		
		log.info("Please enter your Username and Password");
		// Get username
		while (invalid) {
			log.info("Username: ");
			user = sc.nextLine();
			// no whitespace allowed or empty username
			if (!user.contains(" ") || user.isEmpty()) {
				invalid = false;
			} else {
				log.info("Error: Enter a valid username");
			}
		}
		invalid = true;

		// Get password
		while (invalid) {
			log.info("Password: ");
			pass = sc.nextLine();
			// no whitespace in password or empty field
			if (!pass.contains(" ") || pass.isEmpty()) {
				invalid = false;
			} else {
				log.info("Error: Enter a valid password");
			}
		}
		
		// Check if no entry, avoids NullPtrException
		if (accounts.get(user) == null) {
			user = "";
			return false;
		}
		
		// Attempt login
		if (accounts.get(user).equals(pass)) {
			return true;
		}
		// reset user if failed login
		user = "";
		return false;
	}
	
	public void newAccount() {
		boolean invalid = true;
		String pass = "";
		String newUser = "";
		
		// Get username
		while (invalid) {
			log.info("\nPlease enter desired username");
			log.info("Username: ");
			newUser = sc.nextLine();
			// Do not allow whitespace in username or empty field
			if (!newUser.contains(" ") || newUser.isEmpty()) {
				invalid = false;
			} else {
				log.info("Error: Enter a valid username. Username cannot contain whitespace");
				continue;
			}
			// Check if username exists
			if (accounts.get(newUser) != null) {
				log.info("Error: Username already exists");
				invalid = true;
			}
		}
		invalid = true;
		// Get password
		while(invalid) {
			log.info("Please enter desired password");
			log.info("Password: ");
			pass = sc.nextLine();
			//Do not allow whitespace in password or empty field
			if (!pass.contains(" ") || pass.isEmpty()) {
				invalid = false;
			} else {
				log.info("Error: Enter a valid password. Password cannot contain whitespace");
			}
		}
		
		// Add account to accounts, balances, and SQL DB
		accounts.put(newUser, pass);
		balancesBig.put(newUser, new BigDecimal(0));
		String combined = "\n" + newUser + " " + pass; 
		String newBalance = "\n" + newUser + " 0";

		log.info("Account Created!");
	}
	
	public void loggedInMenu() {
		int operation = 0;
		while (operation != 4) {
			// Get input
			log.info("\nLogged in as " + user + ". Select an option\n"
					+ "1. Deposit\n"
					+ "2. Withdraw\n"
					+ "3. View Balance\n"
					+ "4. Log Out");
			String input = sc.nextLine();
			// Check if input is valid.
			if ( (input.length() != 1) || (!Character.isDigit(input.charAt(0))) || (Integer.valueOf(input) > 4) ) {
				log.info("Error: Please enter a valid option");
				loggedInMenu();
				return;
			}
			// update operation
			operation = Integer.valueOf(input);

			// Deposit
			if (operation == 1) {
				deposit();
			}
			// Withdraw
			else if (operation == 2) {
				withdraw();
			}
			// Check balance
			else if (operation == 3) {
				balance();
			}
		}
	}
	
	private void deposit() {
		log.info("\nHow much would you like to deposit? (Do not include '$' or decimals)");
		String input = sc.nextLine();
		Integer deposit = 0;
		// Check if input is valid
		if (input.length() < 1) {
			log.info("Error: Invalid input");
			return;
		}
		// Check if input is numerical
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i))) {
				log.info("Error: Input was not numerical");
				return;
			}
		}
		// Convert to Integer
		deposit = Integer.parseInt(input);
		
		// Deposit money
		balances.replace(user, balances.get(user) + deposit);
		log.info("$" + deposit + " has been deposited");
	}
	
	private void withdraw() {
		log.info("\nHow much would you like to withdraw? (Do not include '$' or decimals)");
		log.info("$" + balances.get(user) + " available for withdrawal");
		String input = sc.nextLine();
		Integer withdraw = 0;
		Integer difference = 0;
		// Check if input is valid
		if (input.length() < 1) {
			log.info("Error: Invalid input");
			return;
		}
		// Check if input is numerical
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i))) {
				log.info("Error: Input was not numerical");
				return;
			}
		}
		// Convert to Integer
		withdraw = Integer.parseInt(input);
		
		// Check if withdrawal amount is valid
		difference = balances.get(user) - withdraw;
		if (difference < 0) {
			log.info("Error: Cannot withdraw more than available");
			return;
		}
		
		// Update balances
		balances.replace(user, difference);
		log.info("You have withdrawn " + difference);
	}
	
	public void balance() {
		log.info("Current balance for " + user + " is " + balances.get(user));
	}
	
	public void exit() {
		updateLogins();
		updateBalances();
	}
	
	private void updateBalances() {
		String combined = "";
		try {
			// Write to login.txt
			File file = new File(pathBalances);

			// Checking to see if file exists. Create file if not.
			if(!file.exists()) {
				file.createNewFile();
			}
						
			// Overwrite file
			FileWriter fw = new FileWriter(file, false);
			bw = new BufferedWriter(fw);
			
			for (Map.Entry<String, Integer> it : balances.entrySet()) {
				combined = it.getKey() + " " + it.getValue() + "\n";
				bw.write(combined);
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateLogins() {
		String combined = "";
		try {
			// Write to login.txt
			File file = new File(pathLogin);

			// Checking to see if file exists. Create file if not.
			if(!file.exists()) {
				file.createNewFile();
			}
						
			// Overwrite file
			FileWriter fw = new FileWriter(file, false);
			bw = new BufferedWriter(fw);
			
			for (Map.Entry<String, String> it : accounts.entrySet()) {
				combined = it.getKey() + " " + it.getValue() + "\n";
				bw.write(combined);
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

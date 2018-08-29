package com.revature.bank;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
	private Map<String, BigDecimal> balancesBig = new HashMap<>();
	private static Scanner sc = new Scanner(System.in);
	private String user;
	private LoginDao ldi = new LoginDaoImpl();
	private Balance b;
	private BalanceDao bdi = new BalanceDaoImpl();
	private static DecimalFormat df = new DecimalFormat("#,###.00");
	
	public BankJDBC() {
		super();
		log.info("Initializing...");
		// Clear out storage if bank constructor is called again somehow
		accounts.clear();
		balancesBig.clear();
		
		// Get login credentials from DB
		ldi = new LoginDaoImpl();
		List<Login> loginList = ldi.getLogins();
		log.info("Loading Users...");
		
		// Populate accounts Map
		for (Login i : loginList) {
			accounts.put(i.getUsername(), i.getPassword());
		}
				
		// Get Balance information from DB
		bdi = new BalanceDaoImpl();
		List<Balance> balanceList = bdi.getBalances();
		log.info("Loading Balances...");
		
		// Populate balances map
		for (Balance i : balanceList) {
			balancesBig.put(i.getUsername(), i.getMoney());
		}
		log.info("Done!\n");
	}
	
	public int menu() {
		log.info("\nPlease select an option\n"
						+ "1. Login\n"
						+ "2. Create New Account\n"
						+ "3. Exit");
		String input = sc.nextLine();
		if ((input.length() != 1) || (!Character.isDigit(input.charAt(0))) || (Integer.valueOf(input) > 3)) {
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
		balancesBig.put(newUser, new BigDecimal("0"));
		Login l = new Login(newUser, pass);
		b = new Balance(newUser, new BigDecimal("0"));
		if (ldi.createLogin(l) != 1) {
			log.info("Error creating account");
			return;
		}
		if (bdi.createBalance(b) != 1) {
			log.info("Error creating balance");
			return;
		}
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
		// Set current user to no one
		user = "";
	}
	
	private void deposit() {
		log.info("\nHow much would you like to deposit? (Do not include '$')");
		String input = sc.nextLine();
		BigDecimal balance = balancesBig.get(user);
		BigDecimal deposit;
		// Check if input is valid
		if (input.length() < 1) {
			log.info("Error: Invalid input");
			return;
		}
		// Check if input is numerical, also handles negative
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i)) && (input.charAt(i) != '.')) {
				log.info("Error: " + input.charAt(i) + " is not a valid input");
				return;
			}
		}
		// Convert to BigDecimal and sum
		deposit = new BigDecimal(input);
		balance = balance.add(deposit);
		
		// Check if deposit is <.01
		if (deposit.compareTo(new BigDecimal(".01")) < 0) {
			log.info("Error: Cannot deposit amount less than $0.01");
			return;
		}
		
		// Deposit money
		balancesBig.replace(user, balance);
		
		// Update DB
		b = new Balance(user, balance);
		bdi.updateBalance(b);
		
		log.info("$" + df.format(deposit) + " has been deposited");
	}
	
	private void withdraw() {
		log.info("\nHow much would you like to withdraw? (Do not include '$')");
		log.info("$" + df.format(balancesBig.get(user)) + " available for withdrawal");
		String input = sc.nextLine();
		BigDecimal withdraw;
		BigDecimal difference;
		// Check if input is valid
		if (input.length() < 1) {
			log.info("Error: Invalid input");
			return;
		}
		// Check if input is numerical, also handles negative
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i)) && (input.charAt(i) != '.')) {
				log.info("Error: " + input.charAt(i) + " is not a valid input");
				return;
			}
		}
		// Convert to BigDecimal
		withdraw = new BigDecimal(input);
		
		// Check if withdrawal amount is valid
		difference = balancesBig.get(user).subtract(withdraw);
		if (difference.compareTo(BigDecimal.ZERO) < 0) {
			log.info("Error: Cannot withdraw more than available");
			return;
		} else if (withdraw.compareTo(new BigDecimal(".01")) < 0) {
			log.info("Error: Cannot withdraw amount less than $0.01");
			return;
		}
		
		// Update balances
		balancesBig.replace(user, difference);
		
		// Update DB
		b = new Balance(user, difference);
		bdi.updateBalance(b);
		
		log.info("You have withdrawn $" + df.format(withdraw));
		log.info("Current balance: $" + df.format(difference));
	}
	
	public void balance() {
		log.info("Current balance for " + user + " is $" + df.format(balancesBig.get(user)));
	}
	
	public void exit() {
		log.info("\nThank you for using our Banking App!");
	}
}

package com.revature.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {

	private Map<String, String> accounts = new HashMap<String, String>();
	private Map<String, Integer> balances = new HashMap<String, Integer>();
	private static Scanner sc = new Scanner(System.in);
	private String user;
	private static String pathLogin = "src/com/revature/bank/login.txt";
	private static String pathBalances = "src/com/revature/bank/balances.txt";
	private BufferedWriter bw;
	
	public Bank() {
		super();
		System.out.println("Initializing...");
		// Clear out storage if bank constructor is called again somehow
		accounts.clear();
		balances.clear();
		try {
			BufferedReader br = new BufferedReader(new FileReader(pathLogin));
			String line = br.readLine();
			String[] dataPair;
			System.out.print("Loading Users...");
			
			// Read in logins file
			while(line != null) {
				// Parse read in string on whitespace
				dataPair = line.split("[\\s]");
				// Add existing user to local account map
				accounts.put(dataPair[0], dataPair[1]);
				line = br.readLine();
			}
			System.out.println("Done");
			br.close();
			
			// Read in Balances
			br = new BufferedReader(new FileReader(pathBalances));
			line = br.readLine();
			System.out.print("Loading Account Balances...");
			
			while(line != null) {
				dataPair = line.split("[\\s]");
				balances.put(dataPair[0], Integer.parseInt(dataPair[1]));
				line = br.readLine();
			}
			System.out.println("Done");
			br.close();
			
			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int menu() {
		System.out.println("\nPlease select an option\n"
						+ "1. Login\n"
						+ "2. Create New Account\n"
						+ "3. Exit");
		String input = sc.nextLine();
		if ( (!(input.length() == 1)) || (!Character.isDigit(input.charAt(0))) || (Integer.valueOf(input) > 3)) {
			System.out.println("Error: Please enter a valid option");
			return menu();
		}
		return Integer.valueOf(input);
	}
	
	public boolean login() {
		boolean invalid = true;
		String pass = "";
		
		System.out.println("Please enter your Username and Password");
		// Get username
		while (invalid) {
			System.out.print("Username: ");
			user = sc.nextLine();
			// no whitespace allowed or empty username
			if (!user.contains(" ") || user.isEmpty()) {
				invalid = false;
			} else {
				System.out.println("Error: Enter a valid username");
			}
		}
		invalid = true;

		// Get password
		while (invalid) {
			System.out.print("Password: ");
			pass = sc.nextLine();
			// no whitespace in password or empty field
			if (!pass.contains(" ") || pass.isEmpty()) {
				invalid = false;
			} else {
				System.out.println("Error: Enter a valid password");
			}
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
			System.out.println("\nPlease enter desired username");
			System.out.print("Username: ");
			newUser = sc.nextLine();
			// Do not allow whitespace in username or empty field
			if (!newUser.contains(" ") || newUser.isEmpty()) {
				invalid = false;
			} else {
				System.out.println("Error: Enter a valid username. Username cannot contain whitespace");
				continue;
			}
			// Check if username exists
			if (accounts.get(newUser) != null) {
				System.out.println("Error: Username already exists");
				invalid = true;
			}
		}
		invalid = true;
		// Get password
		while(invalid) {
			System.out.println("Please enter desired password");
			System.out.print("Password: ");
			pass = sc.nextLine();
			//Do not allow whitespace in password or empty field
			if (!pass.contains(" ") || pass.isEmpty()) {
				invalid = false;
			} else {
				System.out.println("Error: Enter a valid password. Password cannot contain whitespace");
			}
		}
		
		// Add account to accounts, balances, login file, and balances file
		accounts.put(newUser, pass);
		balances.put(newUser, 0);
		String combined = "\n" + newUser + " " + pass; 
		String newBalance = "\n" + newUser + " 0";
		
		bw = null;
		
		try {
			// Write to login.txt
			File file = new File(pathLogin);
			
			// Checking to see if file exists. Create file if not.
			if(!file.exists()) {
				file.createNewFile();
			}
			
			// our FileWriter has an optional argument which specifies if it's appending
			FileWriter fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(combined);
			
			
			// Write to balances.txt
			file = new File(pathBalances);
			
			// Checking to see if file exists. Create file if not.
			if(!file.exists()) {
				file.createNewFile();
			}
			
			// our FileWriter has an optional argument which specifies if it's appending
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			bw.write(newBalance);
			
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
		
		System.out.println("Account Created!");
	}
	
	public void loggedInMenu() {
		int operation = 0;
		while (operation != 4) {
			// Get input
			System.out.println("\nLogged in as " + user + ". Select an option\n"
					+ "1. Deposit\n"
					+ "2. Withdraw\n"
					+ "3. View Balance\n"
					+ "4. Log Out");
			String input = sc.nextLine();
			// Check if input is valid.
			if ( (!(input.length() == 1)) || (!Character.isDigit(input.charAt(0))) || (Integer.valueOf(input) > 4) ) {
				System.out.println("Error: Please enter a valid option");
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
	
	public void deposit() {
		System.out.println("\nHow much would you like to deposit? (Do not include '$' or decimals)");
		String input = sc.nextLine();
		Integer deposit = 0;
		// Check if input is valid
		if (input.length() < 1) {
			System.out.println("Error: Invalid input");
			return;
		}
		// Check if input is numerical
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i))) {
				System.out.println("Error: Input was not numerical");
				return;
			}
		}
		// Convert to Integer
		deposit = Integer.parseInt(input);
		
		// Deposit money
		balances.replace(user, balances.get(user) + deposit);
		System.out.println("$" + deposit + " has been deposited");
		return;
	}
	
	public void withdraw() {
		System.out.println("\nHow much would you like to withdraw? (Do not include '$' or decimals)");
		System.out.println("$" + balances.get(user) + " available for withdrawal");
		String input = sc.nextLine();
		Integer withdraw = 0;
		Integer difference = 0;
		// Check if input is valid
		if (input.length() < 1) {
			System.out.println("Error: Invalid input");
			return;
		}
		// Check if input is numerical
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i))) {
				System.out.println("Error: Input was not numerical");
				return;
			}
		}
		// Convert to Integer
		withdraw = Integer.parseInt(input);
		
		// Check if withdrawal amount is valid
		difference = balances.get(user) - withdraw;
		if (difference < 0) {
			System.out.println("Error: Cannot withdraw more than available");
			return;
		}
		
		// Update balances
		balances.replace(user, difference);
		System.out.println("You have withdrawn " + difference);
		return;
	}
	
	public void balance() {
		System.out.println("Current balance for " + user + " is " + balances.get(user));
		return;
	}
	
	public void exit() {
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
}

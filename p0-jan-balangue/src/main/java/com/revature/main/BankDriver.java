package com.revature.main;

import java.util.Scanner;

import com.revature.bank.User;
import com.revature.io.BankFileReader;

public class BankDriver {
	
	private static Scanner sc = new Scanner(System.in);
	private static BankFileReader bfr = new BankFileReader();
	
	public static void main(String[] args) {
		
		System.out.println("Revature Bank");
		System.out.println("-------------\n");
		System.out.println("Would you like to login, register, or exit?");
		String option = "";
		do {
			option = sc.nextLine();
			switch (option) {
			case "login":
				System.out.println("Enter username: ");
				String userName = sc.nextLine();
				System.out.println("Enter password: ");
				String password = sc.nextLine();
				User user = new User(userName, password);
				if (user.validateIdentity())	{
					System.out.println("User authorized.");
					String transaction = "";
					while(transaction != "Logout") {
						transaction = transactionQuery(user);
					}
				} else {
					System.out.println("Invalid login credentials.");
				}
				break;
			case "register":
				System.out.println("Enter username: ");
				String newUserName = sc.nextLine();
				System.out.println("Enter password: ");
				String newPassword = sc.nextLine();
				String[] checkIfAlreadyRegistered = new String[3];
				checkIfAlreadyRegistered = bfr.readLines("./User.txt");
				if (checkIfAlreadyRegistered[0] == "") {
					User newUser = new User(newUserName, newPassword);
					String transaction = "";
					while(transaction != "Logout") {
						transaction = transactionQuery(newUser);
					}
				} else {
					System.out.println("A user has already been registered. Login instead.");
				}
				break;
			case "exit":
				System.out.println("Thank you for your business. Goodbye.");
				sc.close();
				System.exit(0);
			default:
				System.out.println("Invalid request. Please try again.");
			}
			System.out.println("Would you like to login, register, or exit?");
		} while (option != "exit");
		System.out.println("Thank you for your business. Goodbye.");
		sc.close();
		System.exit(0);
	}
	
	public static String transactionQuery(User user) {
		System.out.println("\nWhat kind of transaction do you want? Please type one of the following: ");
		System.out.println("\tView balance");
		System.out.println("\tCreate transaction");
		System.out.println("\tLogout");
		String transaction = sc.nextLine();
		switch (transaction) {
			case "View balance":
				String[] accountRecords  = new String[3];
				accountRecords = bfr.readLines("./User.txt");
				System.out.println("Account balance:" + accountRecords[2]);
				break;
			case "Create transaction":
				System.out.println("Enter transaction type: deposit or withdrawal?");
				String transactionType = sc.nextLine();
				System.out.println("Enter amount:");
				String amountStr = sc.nextLine();
				double transactionAmount;
				try {
					transactionAmount = Double.parseDouble(amountStr);
					if (transactionAmount < 0) {
						System.out.println("Error: cannot have a negative transaction amount.");
					} else {
						user.performTransaction(transactionType, transactionAmount);
					}
					
				} catch (Exception e) {
					System.out.println("Invalid transaction amount.");
				}
				break;
			case "Logout":
				System.out.println("You are now logged out.");
				return "Logout";
			default:
				System.out.println("Invalid transaction type.");
		}
		return transaction;
	}
}

package com.revature.bank;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Starting...");
		int operation = 0;
		Bank bank = new Bank();
		
		System.out.println("Welcome to the Bank.");
		operation = bank.menu();
		while (operation != 3) {
			// Login
			if (operation == 1) {
				if(bank.login()) {
					System.out.println("Successfully logged in");
					bank.loggedInMenu();
				} else {
					System.out.println("No account with given credentials");
				}
			}
			// New Account
			if (operation == 2) {
				bank.newAccount();
			}
			
			// Back to main menu
			operation = bank.menu();
			
		}
		bank.exit();
		System.out.println("\nThank you for using our Banking App!");
	}

}

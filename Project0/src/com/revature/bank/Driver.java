package com.revature.bank;

import org.apache.log4j.Logger;

public class Driver {

	private static Logger log = Logger.getRootLogger();

	public static void main(String[] args) {
		log.info("Starting...");
		int operation = 0;
		BankJDBC bank = new BankJDBC();
		
		log.info("Welcome to the Bank.");
		operation = bank.menu();
		while (operation != 3) {
			// Login
			if (operation == 1) {
				if(bank.login()) {
					log.info("Successfully logged in");
					bank.loggedInMenu();
				} else {
					log.info("No account with given credentials");
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
	}

}

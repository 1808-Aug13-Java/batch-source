package com.revature.bank;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.LoginDao;
import com.revature.dao.LoginDaoImpl;
import com.revature.model.Login;

public class Driver {

	private static Logger log = Logger.getRootLogger();

	
	public static void main(String[] args) {
//		log.info("Starting...");
//		int operation = 0;
//		Bank bank = new Bank();
//		
//		log.info("Welcome to the Bank.");
//		operation = bank.menu();
//		while (operation != 3) {
//			// Login
//			if (operation == 1) {
//				if(bank.login()) {
//					log.info("Successfully logged in");
//					bank.loggedInMenu();
//				} else {
//					log.info("No account with given credentials");
//				}
//			}
//			// New Account
//			if (operation == 2) {
//				bank.newAccount();
//			}
//			
//			// Back to main menu
//			operation = bank.menu();
//			
//		}
//		bank.exit();
//		log.info("\nThank you for using our Banking App!");
		LoginDao ldi = new LoginDaoImpl();
		List<Login> loginList = ldi.getLogins();
	}

}

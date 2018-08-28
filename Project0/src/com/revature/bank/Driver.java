package com.revature.bank;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.revature.dao.BalanceDao;
import com.revature.dao.BalanceDaoImpl;
import com.revature.dao.LoginDao;
import com.revature.dao.LoginDaoImpl;
import com.revature.model.Balance;
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
		new BankJDBC();
		
//		BalanceDao bdi = new BalanceDaoImpl();
//		List<Balance> balanceList = bdi.getBalances();
//		for (Balance i : balanceList) {
//			log.info(i);
//		}
		LoginDao ldi = new LoginDaoImpl();
		Login l = new Login("user", "password");
//		log.info(ldi.createLogin(l));
		log.info(ldi.deleteLoginById(l.getUsername()));
		log.info(ldi.createLogin(l));
	}

}

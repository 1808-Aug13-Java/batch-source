package com.revature.main;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bank.Account;
import com.revature.bank.Customer;
import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.CustomerDAO;
import com.revature.dao.CustomerDAOImpl;
//import com.revature.io.BankFileReader;

public class BankDriver {
	
	private static Scanner sc = new Scanner(System.in);
//	private static BankFileReader bfr = new BankFileReader();
	private static Logger log = Logger.getRootLogger();
	
	
	public static void main(String[] args) {
		
		log.info("Revature Bank");
		log.info("-------------\n");
		log.info("Would you like to 1. login, 2. register, or 3. exit?");
		String option = null;
		do {
			option = sc.nextLine();
			switch (option) {
			case "login":
			case "1":
				log.info("Enter username: ");
				String customerName = sc.nextLine();
				log.info("Enter password: ");
				String password = sc.nextLine();
				Customer customer = new Customer(customerName, password);
				if (customer.validateIdentity(customerName, password))	{
					log.info("User authorized.");
					String transaction = "";
					while(transaction != "Logout") {
						transaction = transactionQuery(customer);
					}
				} else {
					log.error("Invalid login credentials.");
				}
				break;
			case "register":
			case "2":
				log.info("Enter username: ");
				String newCustomerName = sc.nextLine();
				log.info("Enter password: ");
				String newPassword = sc.nextLine();
				register(newCustomerName, newPassword);
				break;
			case "exit":
			case "3":
				log.info("Thank you for your business. Goodbye.");
				sc.close();
				System.exit(0);
				break;
			default:
				log.error("Invalid request. Please try again.");
			}
			log.info("Would you like to 1. login, 2. register, or 3. exit?");
		} while (option != "exit" || option != "3");
	}
	
	public static void register (String customerName, String password) {
		CustomerDAO cdi = new CustomerDAOImpl();
		Customer c = new Customer();
		c.setCustomerName(customerName);
		c.setPassword(password);
		List<Customer> allCustomers = cdi.getCustomers();
		boolean isCustomerRegistered = false;
		for (Customer customer: allCustomers) {
			if (customer.getCustomerName().equals(customerName) &&
					customer.getPassword().equals(password)) {
				log.error("You are already registered. Login instead.");
				isCustomerRegistered = true;
				c = null;
				break;
			}
		}
		if (!isCustomerRegistered) {
			cdi.createCustomer(c);
			transactionQuery(c);
		}
		
//		String[] checkIfAlreadyRegistered = bfr.readLines("./User.txt");
//		if (checkIfAlreadyRegistered[0] == "") {
//			Customer newCustomer = new Customer(customerName, password);
//			String transaction = "";
//			while(transaction != "Logout") {
//				transaction = transactionQuery(newCustomer);
//			}
//		} else {
//			log.error("A user has already been registered. Login instead.");
//		}
	}
	
	public static String transactionQuery(Customer customer) {
		log.info("\nWhat kind of transaction do you want? Please type one of the following: ");
		log.info("\t1. View balance");
		log.info("\t2. Create transaction");
		log.info("\t3. Logout");
		String transaction = sc.nextLine();
		switch (transaction) {
			case "View balance":
			case "1":
				AccountDAO adi = new AccountDAOImpl();
//				List<Account> allAccounts = adi.getAccounts();
//				boolean accountFound = false;
//				for (Account account: allAccounts) {
//					if (account.getCustomerId() == customer.getCustomerId()) {
				Account account = adi.getAccountByCustomerId(customer.getCustomerId());
				if (account != null && account.getCustomerId() == customer.getCustomerId()) {
					log.info("Account found");
					log.info("Type: " + account.getAccountType());
					log.info("Balance: " + account.getBalance(account.getAccountId()));
//						accountFound = true;
//					}
				} else {
					log.info("You need to create an account.");
					createAccount(customer.getCustomerId());
				}
//				String[] accountRecords = bfr.readLines("./User.txt");
//				log.info("Account balance:" + accountRecords[2]);
				break;
			case "Create transaction":
			case "2":
				log.info("Enter transaction type: 1. deposit or 2. withdrawal?");
				String transactionType = sc.nextLine();
				log.info("Enter amount:");
				String amountStr = sc.nextLine();
				double transactionAmount;
				try {
					transactionAmount = Double.parseDouble(amountStr);
					if (transactionAmount <= 0) {
						log.error("Error: cannot have a negative or zero transaction amount.");
					} else {
						customer.performTransaction(customer.getCustomerId(), transactionType, transactionAmount);
					}
					
				} catch (NullPointerException e) {
					log.error(e);
				}
				break;
			case "Logout":
			case "3":
				log.info("You are now logged out.");
				return "Logout";
			default:
				log.error("Invalid transaction type.");
		}
		return transaction;
	}
	
	public static void createAccount(int customerId) {
//		String accountType = "Checking";
//		do {
//			log.info("Enter account type:");
//			log.info("1. Checking");
//			log.info("2. Savings");
//			accountType = sc.nextLine();
//		} while (accountType != "1" && accountType != "Checking" &&
//				accountType != "2" && accountType != "Savings"); 
		
		String depositStr = null;
		double deposit = 0;
		do {
			log.info("Enter deposit amount: ");
			depositStr = sc.nextLine();
			deposit = Double.parseDouble(depositStr);
			if (deposit < 50.00 || depositStr == null) {
				log.error("Error: minimum deposit is $50.00.");
			}
		} while (depositStr == null || deposit < 50.00);
		Account a = new Account("checking", deposit, customerId);
		AccountDAO adi = new AccountDAOImpl();
		adi.createAccount(a);
	}
}

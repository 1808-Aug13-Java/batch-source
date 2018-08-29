package com.revature.main;

import java.text.DecimalFormat;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.revature.dao.AccountDaoImpl;
import com.revature.dao.UserDaoImpl;




public class Driver {
	private static Logger log = Logger.getRootLogger();
	
	/*
	 * The accounts and passwords are stored in "LoginAccounts.txt" and "LoginPasswords.txt"
	 * The pairing between accounts and passwords is based on their line position in their
	 * respective files. Username on line 1 has a password of the first line in the passwords
	 * text file.
	 */
	
	
	public static String validatedUsername = "";
	public static double balanceHolder = 0;
	static DecimalFormat df = new DecimalFormat("#.##");
	
	public static void main(String[] args) {

		log.info("Welcome to Henry's Bank\n");
		introText();
	}
	
	public static void introText() {

		log.info("What would you like to do?");
		log.info("1. Login");
		log.info("2. Create Account");
		log.info("3. Exit Bank");
		
		Scanner sc = new Scanner(System.in);
		String input= sc.nextLine();
		
		switch(input) {
		case "1":
			validateLogin();
			menuOptions();
			break;
		case "2":
			createAccount();
			log.info("Your account has successfully been created");
			introText();
			break;
		case "3":
			endBank();
			break;
		default:
			log.info("Invalid input, please try again.");
			introText();
		}
	}

	public static void validateLogin() {
		Scanner sc = new Scanner(System.in);
		log.info("Enter Username: ");
		String un = sc.nextLine();
		log.info("Enter Password: ");
		String pw = sc.nextLine();
		UserDaoImpl udi = new UserDaoImpl();
		validatedUsername = un;
		udi.validateUser(un, pw);
	}
	
	public static void createAccount(){
		log.info("What would you like your username to be: ");
		Scanner sc = new Scanner(System.in);
		String un = sc.nextLine();
		log.info("Please enter a password: ");
		String pw = sc.nextLine();
		UserDaoImpl udi= new UserDaoImpl();
		AccountDaoImpl adi = new AccountDaoImpl();
		double bal = balanceCheck();
		udi.createUser(un, pw);
		adi.createAccount(bal);
	}
	
	public static double balanceCheck() {
		Scanner sc1 = new Scanner(System.in);
		log.info("How much will you be putting as your initial deposit");
		double bal = sc1.nextDouble();
		if (bal<0) {
			log.info("Cannot be a negative number, please try again");
			balanceCheck();
		}
		return bal;
	}
	
	public static void menuOptions() {
		log.info("What would you like to do?");
		log.info("1. View balance");
		log.info("2. Deposit");
		log.info("3. Withdraw");
		log.info("4. Logout");
		Scanner sc = new Scanner (System.in);
		String response = sc.nextLine();
		
		switch(response) {
		case "1":
			int id = getId();
			viewBalance(id);
			break;
		case "2":
			int id1 = getId();
			depositMoney(id1);
			viewBalance(id1);
			break;
		case "3":
			int id2 = getId();
			withdrawMoney(id2);
			viewBalance(id2);
			break;
		case "4":
			introText();
			break;
		default:
			log.info("Invalid input, please try again.");
			menuOptions();
		}
	}
	
	public static int getId() {
		AccountDaoImpl adi = new AccountDaoImpl();
		return adi.getAccId(validatedUsername);
	}
	
	public static void viewBalance(int id) {
		AccountDaoImpl adi = new AccountDaoImpl();
		double balance = adi.getAmount(id);
		log.info("You have " + df.format(balance) + " dollars");
		balanceHolder = balance;
		menuOptions();
	}
	
	public static void getBalance(int id) {
		AccountDaoImpl adi = new AccountDaoImpl();
		double balance = adi.getAmount(id);
		balanceHolder = balance;
	}
	
	public static void depositMoney(int id) {
		AccountDaoImpl adi = new AccountDaoImpl();
		getBalance(id);
		log.info("How much would you like to deposit");
		Scanner sc = new Scanner (System.in);
		double increaseAmount = sc.nextDouble();
		if (increaseAmount <0) {
			log.info("You cannot deposit a negative number, please try again");
			menuOptions();
		}
		adi.increaseAmount(id, increaseAmount);
	}
	
	public static void withdrawMoney(int id) {
		AccountDaoImpl adi = new AccountDaoImpl();
		getBalance(id);
		log.info("How much would you like to withdraw");
		Scanner sc = new Scanner (System.in);
		double decreaseAmount = sc.nextDouble();
		log.info("decreaseAmount is : " + decreaseAmount);
		if (decreaseAmount>balanceHolder) {
			log.info("You don't have enough in your account, please try again");
			menuOptions();
		}
		adi.decreaseAmount(id, decreaseAmount);
	}
	
	public static void endBank() {
		log.info("Goodbye! Thank you for choosing Henry's Bank");
		
	}
	
}

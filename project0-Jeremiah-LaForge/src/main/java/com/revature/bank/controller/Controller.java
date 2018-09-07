package com.revature.bank.controller;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.bank.dao.*;
import com.revature.bank.view.*;

public class Controller {

	private static Set<String> userNameSet = new HashSet<>();
	private static Logger log = Logger.getRootLogger();
	private static Scanner console = new Scanner(System.in);

	private static void loadUsers() {
		UserDaoImp userDI = new UserDaoImp();
		for (User u : userDI.getUsers()) {
			userNameSet.add(u.getUserName());
		}
	}

	private Controller() {
		super();
	}

	public static Account createAccount(String uName, String fName, String lName, String psWord, double bal) {

		Scanner console = new Scanner(System.in);
		loadUsers();
		int setSize = userNameSet.size();
		boolean isNameAvailable = false;
		userNameSet.add(uName);

		while (!isNameAvailable) {
			if (userNameSet.size() == setSize) {
				log.info("That user name has already been taken. Please enter a different user name.");
				uName = console.nextLine();
				userNameSet.add(uName);
			}
			if (userNameSet.size() != setSize) {
				isNameAvailable = true;
			}
		}
		Account newAcc = new Account(uName, bal);
		User newUser = new User(uName, fName, lName, psWord);

		UserDAO userDI = new UserDaoImp();
		AccountDAO accDI = new AccountDaoImp();
		int accCreated = 0;
		int userCreated = userDI.createUser(newUser);
		if (userCreated > 0) {
			accCreated = accDI.createAccount(newAcc);
		}
		if (accCreated > 0) {
			log.info("New account created: " + newAcc.toString());
		} else {
			log.info("Account failed to be created.");
			return null;
		}
		return newAcc;
	}

	public static Account loadAccount(String uName) {
		AccountDAO accDI = new AccountDaoImp();
		return accDI.getAccountByUser(uName);
	}

	public static boolean withdrawal(double withdrawal, Account account) {

		if (withdrawal > account.getBallance()) {
			log.info("withdrawal failed; You requested an amount greater than your account ballance. Your ballance: "
					+ account.getBallance() + " Amount requested: " + withdrawal);
			return false;
		}else if(withdrawal < 0) {
			log.info("withdrawal failed; You requested a negative number to withdrawal. Your ballance: "
					+ account.getBallance() + " Amount requested: " + withdrawal);
			return false;
		}
		account.setBallance(account.getBallance() - withdrawal);
		AccountDAO accDI = new AccountDaoImp();
		int accUpdated = accDI.updateAccount(account);
		if (accUpdated > 0) {
			return true;
		}
		log.info("withdrawal failed.");
		return false;
	}

	public static boolean deposit(double deposit, Account account) {
		
		if(deposit < 0) {
			log.info("Deposit failed; You requested a negative number to deposit. Your ballance: "
					+ account.getBallance() + " Amount requested: " + deposit);
			return false;
		}
		account.setBallance(account.getBallance() + deposit);
		return true;
	}

	public static boolean logIn(String userName) {
		loadUsers();
		Account acc = null;
		while (userName == null) {
			log.info("No user name entered. Please enter a user name.");
			userName = console.nextLine();
		}
		if (userNameSet.contains(userName)) {
			acc = validate(userName);
			if (acc != null) {
				log.info("logged in successfully as: " + acc.toString());
				return true;
			}
		} else {
			while (!userNameSet.contains(userName)) {
				log.info("User name not found. Please enter your user name again."
						+ " If you do not have an account,\n then type \"create\" to create a new account.");
				userName = console.nextLine();
				if (userName.equalsIgnoreCase("create")) {
					return View.createView();
				}
				if (userNameSet.contains(userName)) {
					acc = validate(userName);
					if (acc != null) {
						log.info("logged in successfully as: " + acc.toString());
						return true;
					}
				}
			}
		}
		return false;
	}

	public static Account validate(String userName) {
		UserDAO user = new UserDaoImp();
		Account acc = null;
		String pswd = null;
		
		log.info("Enter your password.");
		pswd = console.nextLine();
		if(user.getUserByName(userName).getPsWord().equals(pswd)) {
			acc = loadAccount(userName);
		}
		return acc;
	}
	
	public static int deleteUser(String userName) {
		
		UserDAO userDI = new UserDaoImp();
		return userDI.deletUserByName(userName);
	}
}

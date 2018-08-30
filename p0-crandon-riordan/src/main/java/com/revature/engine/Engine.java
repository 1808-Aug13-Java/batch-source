package com.revature.engine;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.BankDaoImpl;
import com.revature.dao.TransactionDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.model.Bank;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.validation.Validator;

public class Engine {
	
	private static Engine singleton = null;
	static final Logger logger = Logger.getLogger(Engine.class);
	public static final Scanner sc = new Scanner(System.in);
	private User activeUser = null;

	private Engine() {
		super();
	}
	
	public static Engine getEngine() {
		
		if(singleton == null) {
			singleton = new Engine();
		}
		
		return singleton;
	}
	
	public void start() {
		logger.info("Welcome to RevBanking");
		listCommands();
		String userInput = "";
		
		while(userInput != "exit") {
			
			userInput = sc.nextLine().toLowerCase();
			
			completeAction(userInput);
			listCommands();
		}
		
	}
	
	
	private void listCommands() {
		UserDaoImpl udi = new UserDaoImpl();
		if(activeUser == null) {
			logger.info("Type an option");
			logger.info("register");
			logger.info("login");
			logger.info("exit");
		} else if (udi.userHasBank(activeUser.getUsername())) {
			logger.info("Type an option");
			logger.info("logout");
			logger.info("deposit");
			logger.info("withdraw");
			logger.info("view");
			logger.info("transfer");
			logger.info("history");
			logger.info("exit");
		} else {
			logger.info("Type an option");
			logger.info("create");
			logger.info("logout");
			logger.info("exit");
		}
		
		logger.info("");
		
	}
	
	private void completeAction(String action) {
		if(activeUser == null) {
			noActiveUserActions(action);
		} else {
			activeUserActions(action);
		}
	}
	
	private void noActiveUserActions(String action) {
		switch(action) {
			case "register":
				register();
				break;
			case "login":
				login();
				break;
			case "exit":
				exitProgram();
				break;
			default:
				break;
		}
	}
	
	private void activeUserActions(String action) {
		UserDaoImpl udi = new UserDaoImpl();
		switch(action) {
			case "create":
				createAccount();
				break;
			case "logout":
				logout();
				break;
			case "deposit":
				if(!udi.userHasBank(activeUser.getUsername())) {
					logger.info("You don't have an account.");
					break;
				}
				deposit();
				break;
			case "withdraw":
				if(!udi.userHasBank(activeUser.getUsername())) {
					logger.info("You don't have an account.");
					break;
				}
				withdraw();
				break;
			case "view":
				if(!udi.userHasBank(activeUser.getUsername())) {
					logger.info("You don't have an account.");
					break;
				}
				view();
				break;
			case "history":
				if(!udi.userHasBank(activeUser.getUsername())) {
					logger.info("You don't have an account.");
					break;
				}
				transactionHistory();
				break;
			case "transfer": 
				if(!udi.userHasBank(activeUser.getUsername())) {
					logger.info("You don't have an account.");
					break;
				}
				transfer();
				break;
			case "exit":
				exitProgram();
				break;
			default: 
				break;
		}
	}
	
	private void register() {
		UserDaoImpl udi = new UserDaoImpl();
		User user = new User();
		String username = Validator.getAUsername();
		user.setUsername(username);
		if(!udi.isUsernameUnique(user)) {
			logger.info("Username already taken");
			return;
		}
		String userEmail = Validator.getAnEmail();
		user.setUserEmail(userEmail);
		if(!udi.isUserEmailUnique(user)) {
			logger.info("Email is already in use");
			return;
		}
		String userPassword = Validator.getAPassword();
		user.setUserPassword(userPassword);
		
		udi.createUser(user);
	}
	
	private void login() {
		
		UserDaoImpl udi = new UserDaoImpl();
		logger.info("Enter the username");
		String name = sc.nextLine();
		User user = udi.getUserByName(name);
		if(user == null) {
			logger.info("No user by that name");
			return;
		}
		
		logger.info("");
		logger.info("Enter the password for " + user.getUsername());
		String pw = sc.nextLine();
		
		if(user.getUserPassword().equals(pw)) {
			activeUser = user;
		} else {
			logger.info("Incorrect password");
		}
		
	}
	
	private void createAccount() {
		if(activeUser == null) {
			logger.info("No active user, shouldn't have access");
			return;
		}
		
		BankDaoImpl bdi = new BankDaoImpl();
		UserDaoImpl udi = new UserDaoImpl();
		bdi.createBank(udi.getUserIdByName(activeUser.getUsername()));

	}
	
	private void deposit() {
		BankDaoImpl bdi = new BankDaoImpl();
		Bank bank = bdi.getBankByUserId(activeUser.getId());
		
		bdi.deposit(bank.getId());	
	}
	
	private void withdraw() {
		BankDaoImpl bdi = new BankDaoImpl();
		Bank bank = bdi.getBankByUserId(activeUser.getId());
		bdi.withdraw(bank.getId());	
	}
	
	private void transfer() {
		BankDaoImpl bdi = new BankDaoImpl();
		bdi.transfer(activeUser.getId());
	}
	
	private void transactionHistory() {
		TransactionDaoImpl tdi = new TransactionDaoImpl();
		List<Transaction> transactions = tdi.getTransactionsByUserId(activeUser.getId());
		tdi.logHistory(transactions);
	}
	
	private void view() {
		BankDaoImpl bdi = new BankDaoImpl();
		logger.info("You have $"+Validator.formatDecimals(bdi.viewAmountByUserId(activeUser.getId())));
	}
	
	private void exitProgram() {
		logger.info("Exiting...");
		activeUser = null;
		System.exit(0);
	}

	private void logout() {
		logger.info("Logging out...");
		activeUser = null;
	}
	
	
	
}

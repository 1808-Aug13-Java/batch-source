package com.revature.engine;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.BankDaoImpl;
import com.revature.dao.UserDaoImpl;
import com.revature.model.Bank;
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
		if(activeUser == null) {
			logger.info("register");
			logger.info("login");
			logger.info("exit");
		} else if (new UserDaoImpl().userHasBank(activeUser.getUsername())) {
			logger.info("logout");
			logger.info("deposit");
			logger.info("withdraw");
			logger.info("view");
			logger.info("exit");
		} else {
			logger.info("create");
			logger.info("logout");
			logger.info("deposit");
			logger.info("withdraw");
			logger.info("view");
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
			default:
				break;
		}
	}
	
	private void activeUserActions(String action) {
		switch(action) {
			case "create":
				createAccount();
				break;
			case "logout":
				logout();
				break;
			case "deposit":
				break;
			case "withdraw":
				break;
			case "view":
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

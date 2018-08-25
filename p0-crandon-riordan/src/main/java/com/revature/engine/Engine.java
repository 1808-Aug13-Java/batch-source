package com.revature.engine;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Engine {
	
	private Engine singleton = null;
	static final Logger logger = Logger.getLogger(Engine.class);
	public static final Scanner sc = new Scanner(System.in);
	private int activeUser = 0;

	private Engine() {
		super();
	}
	
	public Engine getEngine() {
		
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
			
		}
		
	}
	
	
	private void listCommands() {
		if(activeUser == 0) {
			logger.info("register");
			logger.info("login");
			logger.info("exit");
		} else {
			logger.info("logout");
			logger.info("deposit");
			logger.info("withdraw");
			logger.info("view");
		}
		
		logger.info("");
		
	}
	
	private void completeAction(String action) {
		if(activeUser == 0) {
			noActiveUserActions(action);
		} else {
			activeUserActions(action);
		}
	}
	
	private void noActiveUserActions(String action) {
		switch(action) {
			case "register":
				break;
			case "login":
				break;
			case "logout":
				break;
			case "exit":
			default:
				break;
		}
	}
	
	private void activeUserActions(String action) {
		switch(action) {
			case "register":
				break;
			case "logout":
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
	
	private void exitProgram() {
		logger.info("Exiting...");
		activeUser = 0;
		System.exit(0);
	}

}

package com.revature.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.BankDaoImpl;
import com.revature.model.Account;

public class Driver {
	static final Logger logger = Logger.getLogger(Driver.class);
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		
		BankDaoImpl bdi = new BankDaoImpl();
		boolean exit = false;
		while(!exit) {
			
			welcomeMessage();
			
			String in = (sc.nextLine()).toLowerCase();
			
			switch(in) {
			case "l":
				logger.info("Enter your username: ");
				String username = sc.nextLine();
				logger.info("Enter your password: ");
				String password = sc.nextLine();
				Account a = bdi.login(username, password);
				boolean logout = true;
				if(a.getUsername() != null & a.getPassword() != null) {
					logout = false;
				}
				else {
					logger.error("Your username and password combination were either invalid"
							+ "\nor don't exist. Please try again.\n");
				}
				while(!logout)
				{
					displayDashboard();
					in = sc.nextLine();
					
					switch(in) {
					case "l":
						logout = true;
						logger.info("\nYou have logged out.\n");
						sc.nextLine();
						
						break;
					case "d":
							a.makeDeposit();
							bdi.updateAccount(a);
							sc.nextLine();
		
						break;
					case "v":
						a.viewBalance();
						sc.nextLine();
						break;
					case "w":						
							a.makeWithdrawal();
							bdi.updateAccount(a);
							sc.nextLine();
						break;
					case "u":
						logger.info("Please enter your new password: ");
						String newpassword = sc.nextLine();
						bdi.updatePassword(a.getUsername(), newpassword);
						break;
						
					default:
						logger.info("Please enter a valid input\n");
					}
				}
				break;
			case "c":
					logger.info("Please enter a new username: ");
					String newusername = sc.nextLine(); 
					logger.info("Please enter a new password: ");
					String newpassword = sc.nextLine(); 
					logger.info("Please enter a starting balance: ");
					String startingbalance = sc.nextLine();
					bdi.createAccount(newusername,newpassword,startingbalance);
				break;
			case "q":
				logger.info("Please enter your username: ");
				String user = sc.nextLine(); 
				logger.info("Please enter your password: ");
				String pass = sc.nextLine(); 
				bdi.quickView(user, pass);
				break;
			case "e":
				exit = true;
				break;
			default:
				logger.info("Please enter a valid input\n");
				sc.nextLine();
			}
		}
	}
	
	public static void welcomeMessage() {
		logger.info("Welcome to A-Bank.\n\nPlease select an option:\n\n"
				+ "L - Login\nC - Create account\nQ - Quick balance check\nE - Exit\n");
	}
	
	public static void displayDashboard() {
		logger.info("Please select an option:\n\n"
				+ "D - Deposit money\nW - Withdraw funds\nL - Logout\n"
				+ "V - View Balance\nU - Update Password\n");
	}

}

package com.revature.bank;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.BankUserDaoImpl;
import com.revature.model.BankUser;


public class Commands {

	private static Scanner sc = new Scanner(System.in);
	private boolean loggedIn = false;
	private String input="";
	private String username="";
	private String passinput="";
	BankUserDaoImpl bdi = new BankUserDaoImpl();
	BankUser bu = null;
	Connection con = null;
	String choice="";
	float money=0.00f;
	DecimalFormat df = new DecimalFormat("0.00");
	Logger log = Logger.getRootLogger();
	public void newAccount(){
		/*
		 * Get user input for username and password
		 * use isThereAUser() to see if the username exists already
		 * if it doesn't exist, use a stored procedure to create the new user.
		 */
		log.info("Please enter your desired username: ");
		username = sc.nextLine();
		
		
		log.info("Please enter your desired password: ");
		passinput = sc.nextLine();
		log.info("Your balance has been initialized to 0.00.");
	}
	public Commands(Connection con) {
		this.con = con;
	}
	public boolean isThereAUser(String user) {
		return bdi.getBankUserByUsername(user, con).getUsername().equals(user);
	}
	public void logIn() {
		String uInput;
		String pInput;
		log.info("Existing or New user?");
		input = sc.nextLine();
		input = input.toLowerCase();
		if(input.equals("new user")) {
			log.info("Creating new user...");
		}else if(input.equals("existing")) {
			log.info("Logging into existing user...");
		}
		log.info("Please input your username: ");
		uInput = sc.nextLine();
		log.info("Please input your password: ");
		pInput = sc.nextLine();
		bu = new BankUser(uInput, pInput, 0.00f);
		newUser(bu);
		bu = bdi.getBankUserByLogin(uInput, pInput, con);
		if(bu.getPassword() != null && bu.getUsername()!= null){
			log.info("Successful login!");
			passinput = pInput;
			username = uInput;
			loggedIn = true;
		}else{
			log.info("Incorrect username or password. Please try again.");
			loggedIn=false;
		}
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void menu() {
		log.info("--------------Main Menu--------------");
		log.info("Deposit/Withdraw/View Balance/Log Out");
		log.info("-------------------------------------");
		choice=sc.nextLine();
		choice=choice.toLowerCase();
		try {
		switch(choice) {
			case "deposit":
				log.info("How much would you like to deposit?");
				input = sc.nextLine();
				money = Float.parseFloat(input);
				if(money<0||money > 9999999.99 || !isTwoDecimalPlaces(money))
				{
					log.info("Input invalid.");
					break;
				}
				deposit(money);
				break;
			case "withdraw":
				log.info("How much would you like to withdraw?");
				input = sc.nextLine();
				money = Float.parseFloat(input);
				while(money<0||money>Float.parseFloat(viewBalance())) {
					log.info("Cannot overdraft account.");
					log.info("Current balance: "+viewBalance());
					log.info("Choose new withdrawal amount:");
					money = sc.nextFloat();
					if(money > 9999999.99 || !isTwoDecimalPlaces(money))
					{
						log.info("Input invalid.");
						break;
					}
				}
				withdraw(money);
				break;
			case "view balance":
				log.info("Your current balance is: "+viewBalance());
				break;
			case "log out":
				logOut();
				break;
			default:
				log.info("Invalid input.");
				break;
		}
		}catch(NumberFormatException e) {
			log.error(e);
		}
	}
	public void deposit(float money) {
		bdi.modifyBalance(username, passinput, money, con);
	}
	public void withdraw(float money) {
		money *= -1;
		bdi.modifyBalance(username, passinput, money, con);
	}
	public String viewBalance() {
		BankUser vbbu = null;
		vbbu = bdi.getBankUserByLogin(username, passinput, con);
		return Float.toString(vbbu.getBalance());
	}
	public void logOut() {
		loggedIn = false;
		log.info("Thank you and have a good day!");
	}
	public boolean isTwoDecimalPlaces(float number) {
		String[] split = ((Float)number).toString().split("\\.");

		return split[1].length()>2;
	}
	public void newUser(BankUser user) {
		bdi = new BankUserDaoImpl();
		bdi.createBankUser(user,con);
	}
}

package com.revature.banking;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankingdao.BankCustomerDaoImpl;
import com.revature.bankingdao.BankingCustomerDao;
import com.revature.bankingmodel.BankCustomer;

public class MenuObject {
	
	private Scanner sc = new Scanner(System.in);
	private static Logger log = Logger.getRootLogger();
	private BankingCustomerDao dataObject = new BankCustomerDaoImpl();
	private Banker myBanker = new Banker();
	
	
	//Input Helper Functions
	public String askName()
	{
		String name = "";
		log.info("What is your full name?");
		name = sc.nextLine();
		return name;
	}

	public String askEmail()
	{
		String email = "";
		char[] emailChars;
		log.info("What is your email address?");
		email = sc.nextLine();
		emailChars = email.toCharArray();
		for(char c : emailChars)
		{
			if(c == '@')
			{
				return email;
			}
		}
		log.info("Please enter a valid email address");
		askEmail();
		return email;
	}
	
	public String askUser()
	{
		String username = "";
		log.info("What would you like as your username?");
		username = sc.nextLine();
		
		return username;
	}
	public String askPass()
	{
		String privateInfo = "";
		log.info("What is your password?");
		privateInfo = sc.nextLine();
		return privateInfo;
	}
	
	public float askInitialBalance()
	{
		float initBalance = 0;
		log.info("How much would you like to put as your initial balance?");
		try 
		{
			initBalance = sc.nextFloat();
			if(initBalance < 0)
			{
				throw new InputMismatchException();
			}
		}catch(InputMismatchException e) {
			log.info("Please enter a valid non-negative dollar amount!");
			askInitialBalance();
		}
		
		return initBalance;
	}
	
	public String askNewUser()
	{
		String input = "";
		log.info("Are you a new user?");
		input = sc.nextLine();
		if(!(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("Yes") || 
				input.equalsIgnoreCase("n") || input.equalsIgnoreCase("No")))
		{
			log.info("Please say yes or no!");
			log.info("(y or n are also valid inputs)");
			input = askNewUser();
		}
		return input;
	}
	
	public void createAccount()
	{
		String name = "";
		String email = "";
		String username = "";
		String privateInfo = "";
		float balance = 0;
		dataObject.createBankCustomer(new BankCustomer(1, name, email, username, privateInfo, balance));
	}
	
	public boolean verifyAccount(String email, String pass)
	{
		//VerifyAccount
		BankCustomer bc = dataObject.getBankCustomerByEmail(email);
		return bc.getPassword().equals(pass);
	}
	
	//Run Menus
	public void login()
	{
		//Display login menu 
		String inputStr = "";
		String name = "";
		String email = "";
		String username = "";
		String privateInfo = "";
		float balance = 0;
		
		//Introduction
		log.info("Welcome to Francis Banking");
		//Ask for input
		inputStr = askNewUser();
		
		if(inputStr.equalsIgnoreCase("y") || inputStr.equalsIgnoreCase("yes"))
		{
			name = askName();
			email = askEmail();
			username = askUser();
			privateInfo = askPass();
			balance = askInitialBalance();
			//Create new account
			dataObject.createBankCustomer(new BankCustomer(1,name, email, username, privateInfo,balance));
			log.info("Account created!");
			login();
		}
		else if(inputStr.equalsIgnoreCase("n") || inputStr.equalsIgnoreCase("No"))
		{
			email = askEmail();
			privateInfo = askPass();
			//Verify login information
			if(verifyAccount(email, privateInfo))
			{
				transactionMenu(dataObject.getBankCustomerByEmail(email).getId());
			}
			else
			{
				log.info("Failed to verify account! Please try again!");
			}
			login();
		}
		else if(inputStr.equalsIgnoreCase("exit"))
		{
			//Exit stack trace 
		}
		else
		{
			log.info("Invalid Input!");
			login();
		}
	}
	
	
	public void transactionMenu(int id)
	{
		//Display transaction options 
		BankCustomer currentCustomer = dataObject.getBankCustomerById(id);
		String choice;
		float amount = 0;
		log.info("What can we do for you today?");
		log.info("(Enter the corresponding number or the name of the action)");
		log.info("1. Withdraw");
		log.info("2. Deposit");
		log.info("3. Check Balance");
		log.info("4. Logout");
		log.info("5. Quit");
		choice = sc.nextLine();
		
			if(choice.equals("1") || choice.equalsIgnoreCase("Withdraw"))
			{
				//Withdrawal
				myBanker.withdraw(currentCustomer);
				transactionMenu(id);	
			} 
			else if(choice.equals("2") || choice.equalsIgnoreCase("Deposit"))
			{
				//Deposit
				myBanker.deposit(currentCustomer);
				transactionMenu(id);
			}
			else if(choice.equals("3") || choice.equalsIgnoreCase("Check Balance"))
			{
				//Show Balance
				log.info("Current Balance is: " + currentCustomer.getBalance());
				transactionMenu(id);
			}
			else if(choice.equals("4") || choice.equalsIgnoreCase("Logout"))
			{
				//Log out
			} 
			else
			{
				log.info("Invalid input!");
				transactionMenu(id);
			}
	}

}

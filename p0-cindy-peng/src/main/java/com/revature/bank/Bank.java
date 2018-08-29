package com.revature.bank;

import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Hashtable;
//import java.util.Scanner;			//I guess private static Scanner in the main means
									//all the packages can use that scanner
//import java.util.Map;
//import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.dao.UserDaoImpl;
import com.revature.models.User;


public class Bank {
	private static Logger log = Logger.getRootLogger(); 
	HashMap<String, String> emailToUser;	//this is for if the user decides to put an
						//email for a username. We can see if theres a corresponding username
	public Bank() 
	{ 
		super(); 
//		users = new HashMap<String, User> ();
		emailToUser = new HashMap<String, String> ();
	}
	
	//function to handle overall login session, will call many helpers
	public void login()
	{
		boolean logOffFlag = false;
		//if nobody's made an account, will try to redirect
//		if(users.isEmpty()) {
//			log.info("Oops! There are no current customers registered to this bank. "
//							   + "\nRedirecting you to the menu where you can create an account.\n");
//			return;
//		}// no users recorded yet
		
		String customer = verifyUser(); //loop until a good set of info is found
		
		if(customer.equals("Quit"))		//if customer keeps entering wrong info, 
			return;				//can decide to quit back to main menu
		//process this user's session
		while(logOffFlag == false)		//loop until "logged out." Then return
		{
			String operationChoice = operationPrompts();
			logOffFlag = performOperation(operationChoice, customer);
		} //while ()
		
		return;
	} //login ()
	
	//helper for login(). just menu of operations. Actual work from other helpers
	public boolean performOperation(String choice, String customer)
	{
		UserDaoImpl userDao = new UserDaoImpl();
		User currUser = userDao.getUserByUsername(customer);
//		User currUser = users.get(customer); 	//copy User object so it's easier to work with
		
		switch(choice) {
		case "a":
			deposit(currUser);	//deposit - 
			return false;
		case "b":
			withdraw(currUser);	//withdraw - 
			return false;
		case "c":
			printBalance(currUser);	//view account balance - 
			return false;
		case "d":
//			logOffFlag = true;	//flag marked to exit logged in while loop
			log.info("Thank you for being a bank customer.\n");
			return true;
		default:
			return false;
		}
	} // performOperation()
	
	// login() ==> performOperation() 
	public void printBalance(User currUser) 
	{
		log.info("This is your current account balance: " 
						   + (float) currUser.getAccountBalance() + "\n");
		//NOTE: Strings can't print floats w/o throwing exception!!!! unless type cast!!!
	}
	// login() ==> performOperation() 
	public void withdraw(User currUser) {
		String message = "Enter the withdrawal amount: ";
		String amount = depositWithdrawPrompt(message);
		
		float minusDollars = Float.valueOf(amount);
		String key = currUser.getUsername();
		float balance = currUser.getAccountBalance();
		
		if(balance < minusDollars)
		{
			log.info("Insufficient funds. You can still deposit more money, " + 
							   "check your current balance, or try to withdraw a " +
							   "different amount.\n");
			return;
		} //while check for a nonnegative withdrawal
			
//		currUser.setAccountBalance(balance - minusDollars);
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.makeWithdrawal(key, minusDollars);
//		users.get(key).setAccountBalance(balance - minusDollars);
	}
	// login() ==> performOperation() 
	public void deposit(User currUser) 
	{
		String message = "Enter the deposit amount: ";
		String amount = depositWithdrawPrompt(message);
		
		float addDollars = Float.valueOf(amount);
		float balance = currUser.getAccountBalance();
		
		currUser.setAccountBalance(balance + addDollars);
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.updateUser(currUser);
//		users.get(key).setAccountBalance(balance + addDollars);
	} //deposit
	
	public String depositWithdrawPrompt(String message)
	{
		String amount = Driver.prompt(message);
		while(!validMoneyFormat(amount))
		{
			log.info("Amount must be only numbers or a dot to indicate cents");
			amount = Driver.prompt(message);
		} //while invalid format
		
		return amount;
	}
	// login() ==> performOperation() ==> deposit()/withdraw() 
	public boolean validMoneyFormat(String s)
	{
		int dec = 0;
		
		if(s == null)			//when I accidentally entered nothing, somewhere out of bounds
			return false;
		
		if(s.length() > 3 && ('.' == s.charAt(s.length() - 3))) //NOTE: dont do (".").equals(). "." is actually '.'
			dec = 3;								//$_.10
		else if(s.length() > 2 && ('.' == s.charAt(s.length() - 2)))	//$_.1
			dec = 2;
		else
		{
			for(int j = 0; j < s.length(); j++)
				if(!Character.isDigit(s.charAt(j)))
					return false;
			return true;	//Passed for loop without returning false means it's fine
		}
			
		int i;
		for(i = 0; i < s.length() - dec; i++)		//check for $000.00 Decimal point and at most 2 digits after
			if(!Character.isDigit(s.charAt(i)))
				return false;
		if(!Character.isDigit(s.charAt(i))) 	//for (dec = 3rd) to last or (dec = 2nd) to last	
			if(s.charAt(i) != '.')				//can be either a digit or '.'
				return false;					//if neither those things, return false
		//keep going in for loop
		for(i = i + 1; i < s.length(); i++)
			if(!Character.isDigit(s.charAt(i)))
				return false;
		return true;
	} //validMoneyFormat()
	
	//helper for login(). After verification, get valid choices 
	public String operationPrompts()
	{
		String generalMenu = "What would you like to do? Please enter a letter choice.\n" + 
			 "A: Deposit\n" + 
			 "B: Withdraw\n" +
			 "C: View balance\n"+
			 "D: Log out\n";
		String choice = Driver.prompt(generalMenu).toLowerCase();
		while(!Driver.checkInput(choice) && !choice.equals("d"))
		{
			log.info("That was not a valid input. Please enter a letter.");
			choice = Driver.prompt(generalMenu).toLowerCase();
		} //while
		
		return choice;
	} // operationPrompts()
	//helper for login(). return two strings in an array that will be a checked username and
	//password
	public String verifyUser()
	{
		//menu prompts 
		String askU = "Enter your username or email or \"Quit\": ";
		String askP = "Enter your password: ";
		String inputUser = Driver.prompt(askU);
		
		if(inputUser.equals("Quit"))	//return early, user goes back to main menu
			return inputUser;
		
		String inputPassword = Driver.prompt(askP);
		while(!checkInputs(inputUser, inputPassword))
		{	
			log.info("Either the username or email provided "+ 
							   "doesn't exist on this system or the password is incorrect. "+
							   "Please re-enter your information.\n");
			inputUser = Driver.prompt(askU);
			if(inputUser.equals("Quit"))	//return early, user goes back to main menu
				return inputUser;
			inputPassword = Driver.prompt(askP);
		} //while
		
		log.info("");// newline between input and Asking for Operation Choice
		String user = inputUser;
		return user;
	}
	//helper for verifyUser (and in turn login()). rtn true/false if 
	//the inputs match an existing account
	public boolean checkInputs(String userN, String pWord)
	{
		//check if user exists or if email exists
		UserDaoImpl userDao = new UserDaoImpl();
		User customer = userDao.getUserByUsername(userN);
		if(customer == null) 
			//username doesnt exist. Maybe email exists though
			if(emailToUser.containsKey(userN)) {	//email was entered. 
				userN = emailToUser.get(userN);	//change userN to have actual username
				customer = userDao.getUserByUsername(userN);
				log.info("went here");
			}
			else
				return false;
		
		if(!pWord.equals(customer.getPassword()))
			return false;
		return true;
//		if(users.get(userN) == null) 		
//		{	
//			if(emailToUser.containsKey(userN))	//email was entered. 
//				userN = emailToUser.get(userN);	//change userN to have actual username
//			else	//key doesnt exist in dictionary && is not an email
//				return false;
//		}
//											
//		//if here, then key exists, just check passwords match
//		if(!pWord.equals(users.get(userN).getPassword()))  
//			return false;									
//		return true;
	} // checkInputs()
			  
	
	
	public void createUser()
	{
		String user = this.getUser();
		String password = getPassword();
		String email = this.getEmail(user);
		//create User instance with these
		User newUser = new User(user, email, password, (float)0.00);
//		users.put(user, newUser);   	//put it in dictionary
		UserDaoImpl userDao = new UserDaoImpl();
		userDao.createUser(newUser);
		log.info("Congratulations on making a new account!");
		log.info("");
		
	} // createUser()

	/*	helper function for createUser
		method to get an email, link to hash map
		get password
		new User object created with these
		Would make it static, but because must compare to emailToUser member, method must
		belong to the object not class
	*/
	
	//helper for createUser
	public String getUser()
	{
		String message = "Enter a username: ";
		
		String username = Driver.prompt(message);
//		while(users.get(username) != null 
//			  || username.equals("Quit") )	//while key already exists, and Quit is reserved
		UserDaoImpl userDao = new UserDaoImpl();
		while(userDao.getUserByUsername(username) != null 
			 || username.equals("Quit") ) //while key already exists, and Quit is reserved
		{
			if(username.equals("Quit"))
				log.info("Sorry but \"Quit\" is reserved as a command");
			else
				log.info("That username is already taken");
			username = Driver.prompt(message);
		}
			
		return username;
	}
	
	
	//helper for createUser
	public String getEmail(String user)	//nonstatic. must be part of object to access emailToUser
	{
		String message = "Enter an email: ";
		String email = Driver.prompt(message);
		
		while(emailToUser.get(email) != null)	//email is already taken
		{
			log.info("That email is already taken");
			email = Driver.prompt(message);
		}
		
		//add email to your hashmap
		emailToUser.put(email, user);
		return email;
	}
	
	//helper for createUser
	public static String getPassword()	//static, dont create bank class to use this method
	{									//doesnt use any of Bank's members
		String message = "Enter a password: ";
		String password = Driver.prompt(message);
		return password;
	}
	
//	@Override
//	public String toString()
//	{
//		//printing a Bank means printing the info inside Dictionary users
//		String string = "";
//		Set set = users.entrySet();
//		Iterator it = set.iterator();		//NOTE: entrySet() returns a
//			//Set of HashMap<String, User> entries. THen you can use Set's iterator() method
//		//can also do
//		//Iterator it = users.entrySet().iterator();
//		while(it.hasNext())
//		{
//			Map.Entry pairItem = (Map.Entry) it.next();			//forgetValue, I overrode its
//															//toString function
//			string += (pairItem.getKey() + ", " + pairItem.getValue() + "\n");
//			//string = iterator.next().value.getUsername()
//		}
//	
//		return string;
//	} // toString()
	
	

			
}

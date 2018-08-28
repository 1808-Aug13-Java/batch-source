package com.revature.finance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;


public class Menu {
	
	private static Logger log = Logger.getRootLogger();
//	INFO,stdout,ConsoleAppender,target,logSystem,out;

	HashMap<String, Account> accountsList= new HashMap<String,Account>();
	private static Scanner sc= new Scanner(System.in);

	
	public void startUp() {

		String path= "src/com/revature/finance/bankingData.txt";
		int count=0;
		String[] info= new String[3];	
		try {
			//we want to read each line and stop once we run out of lines
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
			
			while (line != null)
			{
				info[count]=line;
				count++;

				if(count >2) {
					//place info in account	
					Account acc= new Account(info[0], info[1], Float.valueOf(info[2]));
					accountsList.put(info[0], acc);
					count=0;
				}
				line=br.readLine();
			}
			br.close();
		
		} catch (IOException e) {
			log.error(e.getMessage());

		}	
	}	
	
	public void screen() {
		//the main flow of the program
		Appender asd = null;
		
		log.addAppender(asd);
		

		log.info("Please select the number of your desired action");
		log.info("1. Log into your account");
		log.info("2. Create an account");
		int action= sc.nextInt();
		
		switch(action) {
			case 1:{
				//place in a helper function
				//logging into an account
				log.info("Please enter your username");
				String enteredUsername= sc.next();
				if(accountsList.containsKey(enteredUsername)) {
					//the username has been created and exists in the list of accounts
					Account workingAccount=accountsList.remove(enteredUsername);
					//removes account from List
					log.info("Please enter your password");
					String pass= sc.next();
					if (workingAccount.logIn(pass)) {
						loggedScreen(workingAccount.logIn(pass), workingAccount);
						
						//here is where the logged in account menu begins 
						//move to another funciton where it should be called 																
					
					}	//user is logged in successfully
				}
				else {
					//the username does not match or has been entered incorrectly
					log.info("No such username has been found, please try again");
					screen();
				}
				
	//the loggedin account menu should be called here again so that other transactions can be completed until the logout 
				//funciton it called
	
				Account.logOut(accountsList);
				//insert log out here
				break;
				}//end of case 1
			
			case 2:{
				//user is creating a new account
				String newPassword=null;
				log.info("Enter a username");
				String newUsername=sc.next();
				
				// if username is not present continue, else restart
				if( accountsList.containsKey(newUsername)) {
					log.info("Username already exists, please try again");
					screen();
				}
				log.info(newUsername);
				log.info("Enter a password");
				newPassword= sc.next();
	
				log.info("Enter the amount you would like to initially deposit into your account.");
				float startingBalance=sc.nextFloat();
				Account newAccount= new Account(newUsername, newPassword,startingBalance);
				accountsList.put(newAccount.getUsername(), newAccount);
				log.info("Thank you, please log into you new account to perform and transactions");
				screen();
				
				break;
			}
			
			default:
				log.info("Please input the number of a valid action");
				
				break; //might solve the recursion problem	
		}//end switch control
		screen();//might solve the recursion problem
	}//end of screen method
	
	public void loggedScreen(boolean status, Account workingAccount) {
		Account loggedInAccount=workingAccount;
		if(status) {

			log.info("Success, please select a number of a transaction");
			log.info("1. withdraw, 2. deposit, 3. view balance, or 4. Log out");
			int action=sc.nextInt();

			switch(action){
				case 1:{
					log.info("Enter a withdrawl amount");
					float out=sc.nextFloat();
					loggedInAccount.withdraw(out);
					break;
				}
				case 2:{
					log.info("Enter a deposit amount");
					float in =sc.nextFloat();
					loggedInAccount.deposit(in);

					
					break;
				}
				case 3:{
					loggedInAccount.viewBalance();
					
					break;
				}
				case 4:{
					log.info("You are logged out. Thank you.");
					loggedInAccount.loggedOut();
					accountsList.put(loggedInAccount.getUsername(), loggedInAccount);
					Account.logOut(accountsList);
					
					break;
					
				}
					
				default:
					log.info("You messed up");
					accountsList.put(loggedInAccount.getUsername(), loggedInAccount);
					screen();
				}
		}
		else {
			screen();
		}	
		loggedScreen(loggedInAccount.getLogStatus(),workingAccount);
	}//loggedScreen
	
}//end of public class menu

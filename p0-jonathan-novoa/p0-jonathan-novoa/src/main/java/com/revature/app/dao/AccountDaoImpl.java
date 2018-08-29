package com.revature.app.dao;


import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import org.apache.log4j.Logger;

import com.revature.app.model.Account;
import com.revature.app.util.ConnectionUtil;


public class AccountDaoImpl implements AccountDao {
	
	//DaoImpl data members
	private static Scanner sc= new Scanner(System.in);
	Map<String, Account> accountsList= new HashMap<>();
	private static Logger log = Logger.getRootLogger();

	
	public void getAccounts(){
		//will take the place of startUp
		//populates accountslist with all od the current values 

		String sql="SELECT * FROM ACCOUNTS";
		try (
				Connection con =ConnectionUtil.getConnection();
				Statement s=con.createStatement(); 
				ResultSet rs =s.executeQuery(sql) ;		
				){
			
			while( rs.next() )
			{
			//change this all to relevant account information
			String name=rs.getString("USERNAME");
			String pass=rs.getString("PASS");
			float balance=rs.getFloat("BALANCE");
			Account account = new Account (name,pass,balance,false);
			accountsList.put(name, account);
		}
	} catch (SQLException |IOException e) {
		log.error(e.getMessage());
	} 
	//used to display contents
	}

	public int withdraw(float amount, Account currAccount) {
		int accountsUpdated=0;
		float tempBalance;
		if (currAccount.isLoggedIn()) {
			if (amount> currAccount.getBalance() || (currAccount.getBalance()-amount < 0) || amount <0) {
				log.info("Insufficient Funds to perform transaction.");
			}
			else {
				tempBalance=currAccount.getBalance()-amount;
				currAccount.setBalance(tempBalance);
				String sql = "UPDATE ACCOUNTS"+
						" SET BALANCE = ?"+
						" WHERE USERNAME = ?";

				try (Connection con = ConnectionUtil.getConnection();
						PreparedStatement ps = con.prepareStatement(sql) ){
					
					con.setAutoCommit(false);
					ps.setFloat(1, currAccount.getBalance());
					ps.setString(2, currAccount.getUsername());
					ps.executeQuery();
					con.commit();
					accountsUpdated=1;
				} catch (SQLException | IOException e) {
					log.error(e.getMessage());
				}				
			}
		}
		else
		{
			log.info("Must log in to perform that action");
		}
		return accountsUpdated;
	}

	public int deposit(float amount, Account currAccount) {
		int accountsUpdated=0;
		if (currAccount.isLoggedIn()) {
			if(amount >0) {
				float balance=currAccount.getBalance();
				balance+=amount;
				currAccount.setBalance(balance);

				String sql = "UPDATE ACCOUNTS"+
						" SET BALANCE = ?"+
						" WHERE USERNAME = ?";

				try (Connection con = ConnectionUtil.getConnection();
						PreparedStatement ps = con.prepareStatement(sql) ){
					
					con.setAutoCommit(false);
					ps.setFloat(1, currAccount.getBalance());
					ps.setString(2, currAccount.getUsername());
					ps.executeQuery();
					con.commit();
					accountsUpdated=1;
				} catch (SQLException | IOException e) {
					log.error(e.getMessage());
				} 
			}
			else {
				log.info("You cannot deposit a negative amount into your account.");
			}
		}
		else {
			log.info("Must log in to perform that action.");
		}
		return accountsUpdated;
	}
	public void viewBalance(Account currAccount) {
		if (currAccount.isLoggedIn()) {
			log.info("Your current balance is now: $"+currAccount.getBalance());	
		}
		else {
			log.info("Must log in to perform that action.");
		}
	}



	public void loggedInScreen(boolean status, Account workingAccount) {
		getAccounts();
		Account loggedInAccount=workingAccount;
		if(status) {

			log.info("Please select a number of a transaction");
			log.info("1. withdraw, 2. deposit, 3. view balance, or 4. Log out");
			String enter=sc.next();
			if(inputValid(enter)!=0) {
				loggedInScreen(loggedInAccount.isLoggedIn(),workingAccount);
			}
			int action=Integer.parseInt(enter);

			switch(action){
				case 1:{
					log.info("Enter a withdrawl amount");
					
					float out=sc.nextFloat();
					String with=String.format("%.2f", out);
					float out2=Float.parseFloat(with);
					
					withdraw(out2,loggedInAccount);
					break;
				}
				case 2:{
					log.info("Enter a deposit amount");
					float in =sc.nextFloat();
					String with=String.format("%.2f", in);
					float in2=Float.parseFloat(with);
					deposit(in2,loggedInAccount);
					break;
				}
				case 3:{
					viewBalance(loggedInAccount);
					break;
				}
				case 4:{
					log.info("You are logged out.");
					loggedInAccount.setLoggedIn(false);
					accountsList.put(loggedInAccount.getUsername(), loggedInAccount);
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
		loggedInScreen(loggedInAccount.isLoggedIn(),workingAccount);
		
	}




	public void screen() {
		
			getAccounts();
			//Initial screen user gets
			log.info("Please select the number of your desired action");
			log.info("1. Log into your account");
			log.info("2. Create an account");
			log.info("3. Exit");
			String enter=sc.next();
			if(inputValid(enter)!=0) {
				screen();
			}
			int action=Integer.parseInt(enter);
					//sc.nextInt();
			
	
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
						if (workingAccount.getPassword().equals(pass)) {
							workingAccount.setLoggedIn(true);
							loggedInScreen(workingAccount.isLoggedIn(), workingAccount);
	
						}	//user is logged in successfully
					}
					else {
						//the username does not match or has been entered incorrectly
						log.info("No such username has been found, please try again");
						screen();
					}
					//log out here
					break;
					}//end of case 1
				
				case 2:{
					//user is creating a new account
					
					log.info("Enter a username");
					String newUsername=sc.next();
					if (usernameValid(newUsername) !=0) {
						 screen();
					 }
					// if Username is not present continue, else restart
					if( accountsList.containsKey(newUsername)) {
						log.info("Username already exists, please try again");
						screen();
					}
	
					log.info("Enter a password");
					String newPassword= sc.next();
					if(usernameValid(newPassword)!=0) {
						screen();
					}
					log.info("Enter the amount you would like to initially deposit into your account.");
					String bal=sc.next();
					if(nonFloatBalance(bal)!=0) {
						screen();
					}
					
					float startingBalance=Float.parseFloat(bal);
					
					 if (balanceValid(startingBalance) !=0) {
						 screen();
					 }
					
					Account newAccount= new Account(newUsername, newPassword,startingBalance, false);
					int marker =createAccount(newAccount);//new account is finally created
					if (marker==1) {
						log.info("You're account was created successfully");
						log.info("Thank you, please log into your new account to perform and transactions");
						getAccounts();
						
					}
					else {
						log.info("Your account was not created successfully, please try again.");
					}
					screen();
					break;
				}//case 2
				case 3:{
					System.exit(0);
					break;
				}
				default:
					log.info("Please input the number of a valid action");	
					break; //might solve the recursion problem	
			}//end switch control
			screen();
		}//screen
	
	public int createAccount(Account newAccount) {
		String sql="{call NEW_ACCOUNT(?,?,?)}";
		int success=0;

		try (
				Connection con =ConnectionUtil.getConnection();
				CallableStatement ssmt =con.prepareCall(sql);
				){
			con.setAutoCommit(false);
			ssmt.setString(1,newAccount.getUsername());
			ssmt.setString(2, newAccount.getPassword());
			ssmt.setFloat(3, newAccount.getBalance());
			ssmt.executeQuery();
			con.commit();
			success=1;
			
		} catch (SQLException |IOException e ) {
			log.error(e.getMessage());
		} 
		return success;
	}
	
	public int usernameValid(String user) {
		int result =0;
		
		if(user.isEmpty()) {
			log.info("You must enter something");
			result=1;
		}
		if(user.length()>20) {
			log.info("This field cannot be longer than 20 characters");
			result=2;
		}
		try{
		if(user.equals(null)) {
			log.info("This field cannot be cannot be null");
			result=3;
		}
		
		
		}catch (NullPointerException e) {
			log.error(e.getMessage());
		}

		int index=user.length();
		log.info(user);		for (int i=0;i<index;i++) {
			if(Character.isWhitespace(user.charAt(i))) {
				result=4;
			}
		}
		return result;
	}
	public int balanceValid(float bal) {
		int result=0;
		Float balance=bal;
		if(bal<0) {
			log.info("You cannot start with a negative balance.");
			result=1;
		}
		if(balance.isNaN()) {
			log.info("You must enter a valid number.");
			result=2;
		}
		
		
		return result;
		
	}
	
	public int inputValid(String in) {
		int result=0;
			if (in.length()>1) {
				log.info("Please select one of the menu options");
				result=1;
			}
			try {
				Integer.parseInt(in);
				Float.parseFloat(in);
				Double.parseDouble(in);
				Long.parseLong(in);
			}catch (NumberFormatException e) {
				log.info("Please choose one of the menu choices");
				result=2;
			}
		return result;
	}
	public int nonFloatBalance(String in) {
		int result=0;
		
		try {
			Float.parseFloat(in);
		}catch (NumberFormatException e) {
			log.info("Please enter a valid starting balance");
			result=1;
		}
		
		return result;
	}


}

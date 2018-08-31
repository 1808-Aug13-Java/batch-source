package com.revature.dao;

import org.apache.log4j.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;

import com.revature.transactions.*;
import com.revature.connection.*;


public class BankDaoImp implements BankDao{
	
	private static Logger logger = Logger.getRootLogger();
	private static Scanner scanner = new Scanner(System.in);
	Map<String, AccountTransactions> userList = new HashMap<>();
	

	public int deposit(float sum, AccountTransactions currentAccount) {			//handles deposits
		int uptodateAccount = 0;
		if (currentAccount.isactiveAcc()) {
			if(sum > 0) {
				float currentBalance = currentAccount.getCurrentBalance();
				currentBalance += sum;
				currentAccount.setBalance(currentBalance);
				String sql = "UPDATE BANK + SET BALANCE = ? + WHERE USERNAME = ?";
				try(Connection con = retrieveConnection.getConnection();		//Prepared statement
						PreparedStatement ps = con.prepareStatement(sql)){
					con.setAutoCommit(false);
					ps.setFloat(1, currentAccount.getCurrentBalance());
					ps.setString(2, currentAccount.getUsername());
					con.commit();
					uptodateAccount = 1;
				} catch(SQLException | IOException e) {
					logger.error(e.getMessage());
				}
				
			}
			else {
				logger.info("We're sorry but you cannot deposit a negative amount.");
			}
		}
		else {
			logger.info("Please log in first.");
		}
		return uptodateAccount;
	}
	public int withdraw(float sum, AccountTransactions currentAccount) {		//handles withdraws
		int uptodateAccount = 0;
		float updateBalance;
		if(currentAccount.isactiveAcc()) {
			if(sum > currentAccount.getCurrentBalance() || (currentAccount.getCurrentBalance() - sum < 1)){
				logger.info("It seems you do not have the appropriate funds to withdraw.");
				logger.info("Please try again");
			}else {
				updateBalance = currentAccount.getCurrentBalance() - sum;
				currentAccount.setBalance(updateBalance);
				String sql = "UPDATE BANK" + "SET BALANCE = ?" + "WHERE USERNAME = ?";
				try(Connection con = retrieveConnection.getConnection();
						PreparedStatement ps = con.prepareStatement(sql)){
					con.setAutoCommit(false);
					ps.setFloat(1, currentAccount.getCurrentBalance());
					ps.setString(2, currentAccount.getUsername());
					con.commit();
					uptodateAccount = 1;
				} catch(SQLException | IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
				
		else {
			logger.info("Please log in first.");
		}
		return uptodateAccount;
	}
	
	public void currentBalance(AccountTransactions currentAccount) {			//fetches current balance
		if(currentAccount.isactiveAcc()) {
			logger.info("This is your current balance: $" +currentAccount.getCurrentBalance());
		}
	}
	public void menu() {														//acts as a menu prompting a log in before showing a secondary set of options
		oldAccounts();														
		logger.info("Please choose an option by its corresponding number");
		logger.info("1.Login");
		logger.info("2.Create an account");
		logger.info("3.Exit");
		String space=scanner.next();
		if(validation(space)!=0) {
			menu();
		}
		int action=Integer.parseInt(space);
		switch(action) {
			case 1:{
				logger.info("Please enter your username");
				String enteredUsername= scanner.next();
				if(userList.containsKey(enteredUsername)) {
					AccountTransactions workingAccount = userList.remove(enteredUsername);
					logger.info("Please enter your password");
					String pass= scanner.next();
					if (workingAccount.getPassword().equals(pass)) {
						workingAccount.setactiveAcc(true);
						loginMenu(workingAccount.isactiveAcc(), workingAccount);

					}
				}
				else {
					logger.info("No such username has been found, please try again");
					menu();
				}
				break;
				}
			
			case 2:{
				
				logger.info("Enter a username");
				String newUsername=scanner.next();
				if (usernameOk(newUsername) !=0) {
					 menu();
				 }
				if( userList.containsKey(newUsername)) {
					logger.info("Username already exists, please try again");
					menu();
				}

				logger.info("Please enter a password");
				String newPassword= scanner.next();
				if(usernameOk(newPassword)!=0) {
					menu();
				}
				logger.info("Enter the amount you would like to deposit");
				String bal=scanner.next();
				if(correctBalance(bal)!=0) {
					menu();
				}
				
				float startingBalance=Float.parseFloat(bal);
				
				 if (balanceOk(startingBalance) !=0) {
					 menu();
				 }
				
				AccountTransactions createAccount= new AccountTransactions(newUsername, newPassword,startingBalance, false);
				int marker =createAccount(createAccount);
				if (marker==1) {
					logger.info("You're account is ready");
					logger.info("Now you can login");
					oldAccounts();
					
				}
				else {
					logger.info("Your account was not created successfully, please try again.");
				}
				menu();
				break;
			}
			case 3:{
				System.exit(0);
				break;
			}
			default:
				logger.info("Please input the number of a valid action");	
				break;	
		}
		menu();
	}

	public int createAccount(AccountTransactions createAccount) {			//handles the creation of accounts
		String sql = "{call CREATE_ACC (?,?,?)}";
		int transaction = 0;
		
		try (
				Connection con = retrieveConnection.getConnection();	//Callable Statement
				CallableStatement ssmt =con.prepareCall(sql);
				){
			con.setAutoCommit(false);
			ssmt.setString(1,createAccount.getUsername());
			ssmt.setString(2, createAccount.getPassword());
			ssmt.setFloat(3, createAccount.getCurrentBalance());
			ssmt.executeQuery();
			con.commit();
			transaction=1;
			
		}catch (SQLException | IOException e) {
			logger.error(e.getMessage());
		}
		return transaction;
	}
	public void oldAccounts() {												//pulls the accounts that already exist
		
		String sql = "SELECT * FROM BANK";
		try (
			Connection con = retrieveConnection.getConnection();		//Create Statement
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql); ){
		while(rs.next()) {
			String username = rs.getString("USERNAME");
			String password = rs.getString("PASSWORD");
			float currentBalance = rs.getFloat("BALANCE");
			AccountTransactions account = new AccountTransactions (username, password, currentBalance, false);
			userList.put(username, account);
		}
		}catch(SQLException | IOException e) {
			logger.error(e.getMessage());
		}
	}
	public void loginMenu(boolean state, AccountTransactions currentAccount) {		//Secondary menu that displays more options after login		
		oldAccounts();
		AccountTransactions loggedin = currentAccount;
		if(state) {
			logger.info("Thank you for logging in, press the number for the corresponding action");
			logger.info("1.Deposit, 2.Withdraw, 3. View Balance or 4.Logout");
			String space = scanner.next();
			if(validation(space) != 0) {
				loginMenu(loggedin.isactiveAcc(), currentAccount);
			}
			int feedback = Integer.parseInt(space);
			switch(feedback) {
			case 1:
			{
				logger.info("How much would you like to deposit?");
				float in = scanner.nextFloat();
				String with = String.format("%.2f", in);
				float inD = Float.parseFloat(with);
				deposit(inD, loggedin);
			}
			case 2:
			{
				logger.info("How much would you like to withdraw?");
				float out = scanner.nextFloat();
				String with = String.format("%.2f", out);
				float outW = Float.parseFloat(with);
			}
			case 3:
			{
				currentBalance(loggedin);
				break;
			}
			case 4:
			{
				logger.info("Thank you for doing bussiness with us.");
				loggedin.setactiveAcc(false);
				userList.put(loggedin.getUsername(), loggedin);
			}
			default:
				logger.info("Something went wrong, please try again.");
				userList.put(loggedin.getUsername(), loggedin);
				menu();
			}	
		}else {
			menu();
		}
		loginMenu(loggedin.isactiveAcc(), currentAccount);
	}
	
	public int usernameOk(String user) {							//Handles various forms of validation to ensure information entered is a correct format
		int result =0;
		if(user.isEmpty()) {
			logger.info("A username must be entered.");
			result=1;
		}
		if(user.length()>20) {
			logger.info("Your password is too long please shorten it");
			result=2;
		}
		try{
		if(user.equals(null)) {
			logger.info("Please enter a suitable username");
			result=3;
		}
		}catch (NullPointerException e) {
			logger.error(e.getMessage());
		}
		int index=user.length();
		logger.info(user);		for (int i=0;i<index;i++) {
			if(Character.isWhitespace(user.charAt(i))) {
				result=4;
			}
		}
		return result;
	}
	public int balanceOk(float bal) {
		int result=0;
		Float balance=bal;
		if(bal<0) {
			logger.info("Please enter a valid number.");
			result=1;
		}
		if(balance.isNaN()) {
			logger.info("Please enter a valid number");
			result=2;
		}	
		return result;	
	}
	public int validation(String in) {
		int result=0;
			if (in.length()>1) {
				logger.info("Sorry but that was not a valid input, please try again.");
				result=1;
			}
			try {
				Integer.parseInt(in);
				Float.parseFloat(in);
				Double.parseDouble(in);
				Long.parseLong(in);
			}catch (NumberFormatException e) {
				logger.info("Sorry but that was not a valid input, please try again.");
				result=2;
			}
		return result;
	}
	public int correctBalance(String in) {
		int result=0;
		try {
			Float.parseFloat(in);
		}catch (NumberFormatException e) {
			logger.info("Sorry but that was not a valid input, please try again.");
			result=1;
		}
		return result;
	}
}
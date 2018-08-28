package com.revature.app.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.app.model.Account;
import com.revature.app.model.*;
import com.revature.app.util.ConnectionUtil;

public class AccountDaoImpl implements AccountDao {
	
	//DaoImpl data members
	private static Scanner sc= new Scanner(System.in);
	Map<String, Account> accountsList= new HashMap<String,Account>();
	private static Logger log = Logger.getRootLogger();
	
	public Map<String,Account> getAccounts(){
		//will take the place of startUp
		Map<String,Account> allAccounts= new HashMap<String,Account>();
		String sql="SELECT * FROM ACCOUNTS";
		//change this to return only the values we need from ACCOUNTS 
		
		try (
				Connection con =ConnectionUtil.getConnection();
				Statement s=con.createStatement(); 
				ResultSet rs =s.executeQuery(sql) ;		
				){
			
			while( rs.next() )
			{
			Account account = new Account();
			//change this all to relevant account information
			int employeeId= rs.getInt("EMP_ID");
			e.setId(employeeId);
			
			String name=rs.getString("EMP_NAME");
			e.setName(name);
			
			Date birthday=rs.getDate("BIRTHDAY");
			e.setBirthday(birthday);
			
			float monthlySalary=rs.getFloat("MONTHLY_SALARY");
			e.setMonthlySalary(monthlySalary);
			
			Date hireDate=rs.getDate("HIRE_DATE");
			e.setHireDate(hireDate);
			
			String position=rs.getString("POSITION");
			e.setPosition(position);
			
			int managerId=rs.getInt("MANAGER_ID");
			e.setManagerId(managerId);
			
			int departmentId=rs.getInt("DEPT_ID");
			e.setDeptId(departmentId);
			
			int locationId=rs.getInt("LOC_ID");
			e.setLocationId(locationId);
			
			employeelist.add(e);
		}
	}
		
		return allAccounts;
	};

	public void withdraw(float amount, Account currAccount) {
		float tempBalance;
		if (currAccount.isLoggedIn()) {
			if (amount> currAccount.getBalance() || (currAccount.getBalance()-amount < 0)) {
				log.info("Insufficient Funds to perform transaction.");
			}
			else {
				tempBalance=currAccount.getBalance()-amount;
				currAccount.setBalance(tempBalance);
			}
		}
		else
		{
			log.info("Must log in to perform that action");
		}	
	}

	public void deposit(float amount, Account currAccount) {
		if (currAccount.isLoggedIn() && amount > 0) {
			if(amount >0) {
				float balance=currAccount.getBalance();
				balance+=amount;
				currAccount.setBalance(balance);
			}
			else {
				log.info("You cannot deposit a negative amount into your account.");
			}
		}
		else {
			log.info("Must log in to perform that action.");
		}
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
		Account loggedInAccount=workingAccount;
		if(status) {

			log.info("Success, please select a number of a transaction");
			log.info("1. withdraw, 2. deposit, 3. view balance, or 4. Log out");
			int action=sc.nextInt();

			switch(action){
				case 1:{
					log.info("Enter a withdrawl amount");
					float out=sc.nextFloat();
					withdraw(out,loggedInAccount);
					break;
				}
				case 2:{
					log.info("Enter a deposit amount");
					float in =sc.nextFloat();
					deposit(in,loggedInAccount);
					break;
				}
				case 3:{
					viewBalance(loggedInAccount);
					break;
				}
				case 4:{
					log.info("You are logged out. Thank you.");
					loggedInAccount.setLoggedIn(false);
					accountsList.put(loggedInAccount.getUsername(), loggedInAccount);
					saveData(accountsList);					
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

	public void saveData(Map<String, Account> accountsList) {
		//modify to output three lines at a time 
		//save this for the menu since I need access to the HashMap that I don't have here
		String path= "src/main/java/p0data.txt";
		BufferedWriter bw=null;
		try {
		File file= new File(path);
		
		if(!file.exists()) {
			
				file.createNewFile();		
			} 
		FileWriter fw= new FileWriter(file);
		bw = new BufferedWriter(fw);
		
			for(String s: accountsList.keySet()) {
				Account print=accountsList.get(s);
				bw.write(print.getUsername()+ "\n");
				bw.write(print.getPassword()+"\n");
				Float b=print.getBalance();
				bw.write(b.toString()+"\n");
			}
		log.info("Account Info has been written");
		}
		catch (IOException e) {
			log.error(e.getMessage());
			}
		finally {
			try {
				bw.close();
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			}
	}//saveData

	public void startUp() {
		String path= "src/main/java/p0data.txt";
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
					//place info in account	once you have all the account details
					Account acc= new Account(info[0], info[1], Float.valueOf(info[2]), false);
					accountsList.put(info[0], acc);
					count=0;
				}
				line=br.readLine();
			}
			br.close();
		
		} catch (IOException e) {
			log.error(e.getMessage());
		}	
	}//startUp

	public void screen() {
		//Initial screen user gets
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
				saveData(accountsList);
				//log out here
				break;
				}//end of case 1
			
			case 2:{
				//user is creating a new account
				String newPassword=null;
				log.info("Enter a username");
				String newUsername=sc.next();
				
				// if Username is not present continue, else restart
				if( accountsList.containsKey(newUsername)) {
					log.info("Username already exists, please try again");
					screen();
				}
				log.info(newUsername);
				log.info("Enter a password");
				newPassword= sc.next();
	
				log.info("Enter the amount you would like to initially deposit into your account.");
				float startingBalance=sc.nextFloat();
				Account newAccount= new Account(newUsername, newPassword,startingBalance, false);
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
		
	}//end screen

}

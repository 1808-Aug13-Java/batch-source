package com.revature.finance;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Account {
	private String username;
	private String password;
	
	private float balance;
	private boolean loggedIn=false; //initialize loggedin to be false
	
	public Account(String username, String password, float balance) {
		this.username=username;
		this.password=password;
		this.balance=balance;
		loggedIn=true;
	}
	public String getUsername() {
		return this.username;
	}

	
	public boolean logIn(String user) {
		
		if (user.equals(password)) {
			loggedIn=true;
		}
		else 
		{
			loggedIn=false;
			System.out.println("Password is incorrect.");
		}
		return loggedIn;
	}
	public static void logOut(HashMap<String, Account> accountsList ) {
		//modify to output three lines at a time 
		//save this for the menu since I need access to the HashMap that I don't have here
		String path= "src/com/revature/finance/bankingData.txt";
		BufferedWriter bw=null;
		//String content=" Hello";
//
		try {
		File file= new File(path);
		
		if(!file.exists()) {
			
				file.createNewFile();		
			} 
		FileWriter fw= new FileWriter(file);
		bw = new BufferedWriter(fw);
		
			for(String s: accountsList.keySet()) {
				Account print=accountsList.get(s);
				bw.write(print.username+ "\n");
				bw.write(print.password+"\n");
				Float b=print.balance;
				bw.write(b.toString()+"\n");
			}
		

		System.out.println("Account Info has been written");
		}
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		finally {
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}

	
	
	public void withdraw(float amount) {
		if (loggedIn == true) {
			if (amount> balance || (balance-amount < 0)) {
				System.out.println("Insufficient Funds to perform transaction.");
			}
			else {
				balance-=amount;
			}
		}
		else
		{
			System.out.println("Must log in to perform that action");
		}	
	}
	
	public void deposit(float amount) {
		if (loggedIn== true && amount > 0) {
			balance+=amount;
		}
		else {
			System.out.println("Must log in to perform that action.");
		}
	}
	
	public float viewBalance() {
		if (loggedIn==true) {
			return balance;
		}
		else {
			System.out.println("Must log in to perform that action.");
			return 0;
		}
		
		
		
	}
	
	
	
	
	
}

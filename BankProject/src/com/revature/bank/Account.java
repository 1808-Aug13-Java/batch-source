package com.revature.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Account {
	
	private static String username;
	private static String password;
	private static double balance;
	private ArrayList<String[]> Bank = new ArrayList<String[]>();
	
	public void makeDeposit(double funds) {
		balance = balance + funds;
		System.out.println("Your new balance is $" + balance + ".\n");
		
	}
	
	public void makeWithdrawal(double funds) {
		if(balance >= funds) {
			balance = balance - funds;
			System.out.println("Your new balance is $" + balance + ".\n");
		}
		else {
			System.out.println("You don't have enough funds.\n");
		}
	}
	
	public void displayBalance() {
		System.out.println("Your current balance is $" + balance + ".\n");
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(int b) {
		balance = b;
	}

	public void setUsername(String u) {
		username = u;
	}

	public void setPassword(String p) {
		password = p;
	}

	public String getUsername() {
		//System.out.println("Please enter your username:");
		return username;
	}
	
	public String getPassword() {
		//System.out.println("Please enter your password:");
		return password;
	}
	
	public void read() {
		String path = "src/com/revature/bank/bank.txt";
		

		try {

			BufferedReader br = new BufferedReader(new FileReader(path));

			String line = br.readLine();

			

			while(line != null) {

				Bank.add(line.split("\t"));
				line=br.readLine();

			}

			

			br.close();

		

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
	}
	
	public boolean login(String u, String p) {
		boolean found = false;
		for(String[] b : Bank) {
			if(u.equals(b[0])) {
				found = true;
				if(p.equals(b[1])) {
					username = b[0];
					password = b[1];
					balance = Double.parseDouble(b[2]);
					System.out.println("\nWelcome " + username + "\n");
					return false;
				}
				else {
					System.out.println("Incorrect password.");
				}
			}
		}
		if(!found) {
			System.out.println("Username not found.");
		}
		return true;
		
	}

	public void Logout() {
		for(String[] b : Bank) {
			if(username.equals(b[0])) {
				if(password.equals(b[1])) {
					b[2] = Double.toString(balance);
					break;
				}
			}
		}
		
		String path = "src/com/revature/bank/bank.txt";

		try {

		//specify the file we want to write to

		File file = new File(path);

		

		//checking first to see if the file exists, creating it if it doesn't

		if(!file.exists()) {

				file.createNewFile();

		}

		

		// our FileWriter has an optional argument which specifies if it's appending to the file

		FileWriter fw = new FileWriter(file);

		BufferedWriter bw = new BufferedWriter(fw);

		for(String[] b : Bank) {
			
			bw.write(b[0] + "\t" + b[1] + "\t" +
			b[2] + "\n");
			
		}
		

		bw.close();

		

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		
		
		
	}

	public void createAccount(String username, String password, Double balance) {
		String[] newaccount = {username, password, Double.toString(balance)};
		Bank.add(newaccount);
	}

}

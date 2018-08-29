package com.revature.banking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataMaster {

	private double balance;
	
	private String emailAddress;
	private String username;
	private String password;
	
	public void retrieveAccount()
	{
		//TODO: Retrieve our account information from a stored text file
		String path = "src/com/revature/banking/data.txt";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			emailAddress= br.readLine();
			username = br.readLine();
			password = br.readLine();
			balance = Double.valueOf(br.readLine());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean verifyAccount(String email, String user, String pass)
	{
		//First retrieve account info
		retrieveAccount();
		
		//Check if the inputed account information matches the saved information
		if(email.equals(emailAddress) && user.equals(username) && pass.equals(password))
		{
			System.out.println("Account Verified!");
			return true;
		}
		return false;
	}
	
	public void saveAccount(boolean isNewAccount)
	{
		//Write account info to text file
		// Write account info into a text file
				String path = "src/com/revature/banking/data.txt";
				BufferedWriter bw = null;
				
				try {
				//specify the file we want to write to
				File file = new File(path);
				
				//checking first to see if the file exists, creating it if it doesn't
				if(!file.exists())
				{
					file.createNewFile();
				}
				
				// Our FileWriter has an optional argument which specifies if it's aappending to the file 
				FileWriter fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				
				bw.write(emailAddress);
				bw.newLine();
				bw.write(username);
				bw.newLine();
				bw.write(password);
				bw.newLine();
				
				if(isNewAccount)
				{
					bw.write("0");
				}
				else
				{
					bw.write(Double.toString(balance));
				}
				
				bw.close();
				
			} catch(IOException e) {
				e.printStackTrace();
			} 
		
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

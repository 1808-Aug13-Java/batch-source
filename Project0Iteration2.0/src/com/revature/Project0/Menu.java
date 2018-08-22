package com.revature.Project0;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;



public class Menu {
	HashMap<String, Account> accountsList= new HashMap<String,Account>();
	private static Scanner sc= new Scanner(System.in);
	String y;
	String x;
	
	public void startUp() {
		//reads the data stored in the txt file and populates the HashMap with it
		String path= "src/com/revature/Project0/bankingData.txt";
		//sHashMap<String, String> names= new HashMap<String, String>();
		//String[] readName =new String[3];
		//int i=0;
		//String readPass;
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
				//System.out.println(line);
				if(count >2) {
					//place info in account	
					Account acc= new Account(info[0], info[1], Float.valueOf(info[2]));
					accountsList.put(info[0], acc);
				//	System.out.println(acc);
					count=0;
				//	System.out.println(count);
				}
				
				
				
				line=br.readLine();
				// 
			
			}
			br.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	public void screen() {
		//the main flow of the program
	
		System.out.println("Please select the number of your desired action");
		System.out.println("1. Log into your account");
		System.out.println("2. Create an account");
		if (!sc.hasNextInt()) {
			System.out.println("invalid input");
			screen();
			
		}
		int action= sc.nextInt();
		switch(action) {
		case 1:{
			//place in a helper function
			//logging into an account
			System.out.println("Please enter your username");
			String enteredUsername= sc.next();
			if(accountsList.containsKey(enteredUsername)) {
				//the username has been created and exists in the list of accounts
				Account workingAccount=accountsList.remove(enteredUsername);
				//removes account from List
				System.out.println("Please enter your password");
				String pass= sc.next();
				if (workingAccount.logIn(pass)) {
					System.out.println("Success, please select a number of a transaction");
					System.out.println("1. withdraw, 2. deposit or 3. view balance");
					action=sc.nextInt();
				//	String transaction=sc.next();
					//transaction=transaction.toLowerCase();
					switch(action){
						case 1:{
							System.out.println("Enter a withdrawl amount");
							float out=sc.nextFloat();
							workingAccount.withdraw(out);
							System.out.println("Your current balance is now: "+workingAccount.viewBalance());
							accountsList.put(enteredUsername, workingAccount);
						break;
						}
						case 2:{
							System.out.println("Enter a deposit amount");
							float in =sc.nextFloat();
							workingAccount.deposit(in);
							accountsList.put(enteredUsername, workingAccount);
							break;
						}
						case 3:{
							System.out.println(workingAccount.viewBalance());
							accountsList.put(enteredUsername, workingAccount);
							break;
						}
						default:
							System.out.println("You messed up");
							accountsList.put(enteredUsername, workingAccount);
							screen();
						}
				
				}//user is logged in succesfully
				
			
			}
			else {
				//the username does not match or has been entered incorrectly
				System.out.println("No such username has been found, please try again");
				screen();
			}
			
			Account.logOut(accountsList);
			//insert log out here
			break;
			}//end of case 1
		
		case 2:{
			String newPassword=null;
			System.out.println("Enter a username");
			String newUsername=sc.next();
			
			// if username is not present continue, else restart
			if( accountsList.containsKey(newUsername)) {
				System.out.println("Username already exists, please try again");
				screen();
			}
			System.out.println(newUsername);
			
			//y=newUsername;
			System.out.println("Enter a password");
			newPassword= sc.next();

			System.out.println("Enter the amount you would like to initially deposit into your account.");
			float startingBalance=sc.nextFloat();
			Account newAccount= new Account(newUsername, newPassword,startingBalance);
			accountsList.put(newAccount.getUsername(), newAccount);
			System.out.println("Thank you, please log into you new account to perform and transactions");
			screen();
			
			break;
		}
		default:
			System.out.println("Please input the number of a valid action");
			screen();
		
		}
		
	
	}
	
	
}

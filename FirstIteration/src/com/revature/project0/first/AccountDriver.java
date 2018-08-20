package com.revature.project0.first;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AccountDriver {

	private static Scanner scan = new Scanner(System.in);
	private static Account currentUser;
	
	public static void main(String[] args) {
		System.out.print("Welcome to the bank, enter your UserName: \n");
		currentUser = login(scan.nextLine());
		
		printMenu();
		String menuOption = scan.nextLine(); 
		while (!menuOption.equals("5") && !menuOption.equals("Logout"))
		{
			switch(menuOption){
				case"1": case"Withdrawl":
					System.out.println("Your new balance is $" + withdraw());
					break;
				case"2": case"Deposit":
					System.out.println("Your new balance is $" + deposit());
					break;
				case"3": case"Balance":
					System.out.println("Your balance is $" + checkBalance());
					break;
				case"4": case"History":
					viewHistory();
					break;
				case"5": case"Logout":
					System.out.println("Okay, quit");
					break;
				default:
					System.out.println("Invalid option chosen, please use 1-5, or a specific word to choose your option.");
			}
			printMenu();
			menuOption = scan.nextLine(); 
		}
		
		logout(currentUser);
		System.out.println("Thank you, come again!");
	}
	
	private static Account login(String user) {
		currentUser = new Account();
		File userFile = new File("./" + user + ".txt");
		if (!userFile.exists())
			try {//maybe add addition of new account questions
				userFile.createNewFile();
				System.out.println("Create a one-word password for " + user + "\n");
				String password = scan.nextLine();
				
				currentUser.setUserName(user);
				currentUser.setPassword(password);
			} catch (IOException e) {
				e.printStackTrace();
				//shouldn't trigger
			}
		else
		{
			currentUser.setUserName(user);
			System.out.print("Enter password for " + user + "\n");
			String[] userStringArray = getUserFile(user).split(" ");
			if(!scan.nextLine().equals(userStringArray[1])){
				System.out.println("Incorrect password");
				return login(user);
			}
			currentUser.setUserName(userStringArray[0]);
			currentUser.setPassword(userStringArray[1]);
			currentUser.setBalance(Double.parseDouble(userStringArray[2]));
			//pulling the history of transactions from userStringArray
			String[] tmpArray = Arrays.copyOfRange(userStringArray, 3, userStringArray.length);
			ArrayList<Double> tmpDouble = new ArrayList<Double>();
			for (String str : tmpArray)
				if(!str.isEmpty())
					tmpDouble.add(Double.parseDouble(str));
			
			currentUser.setHistory(tmpDouble);			
		}

		return currentUser;
	}
	
	private static void logout(Account user) {
		try {
			File userFile = new File("./" + user.getUserName() + ".txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(userFile));
			bw.write(user.toString());
			bw.close();
			currentUser = null;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Double withdraw() {
		System.out.println("What amount to withdraw?");
		String input = scan.nextLine();
		Double result = 0.0d;
		try {
			result = (Double) Double.parseDouble(input);
			if (result > currentUser.getBalance()) {
				System.out.println("Insufficient Funds.");
				return currentUser.getBalance();
			}
			currentUser.adjustBalance(-result);
		}catch (NumberFormatException e) {
			System.out.println("Not a valid input");
			withdraw();
		}
		currentUser.appendHistory(-result);
		return currentUser.getBalance();
	}
	
	private static Double deposit() {
		System.out.println("What amount to deposit?");
		String input = scan.nextLine();
		Double result = 0.0d;
		try {
			result = (Double) Double.parseDouble(input);
			currentUser.adjustBalance(result);
		}catch (NumberFormatException e) {
			System.out.println("Not a valid input");
			withdraw();
		}
		currentUser.appendHistory(result);
		return currentUser.getBalance();	
	}
	
	private static Double checkBalance() {
		return currentUser.getBalance();		
	}
	
	private static void viewHistory() {
		System.out.println("Current balance: $" + currentUser.getBalance());
		for (Double hist : currentUser.getHistory())
			System.out.println((hist > 0? "Deposit: $" : "Withdrawl: $") + String.valueOf(hist));
	}
	
	private static String getUserFile(String name) {
		try {
			return new BufferedReader(new FileReader("./" + name + ".txt")).readLine();
		}catch (Exception e) {
			return null;
		}
	}
	
	private static void printMenu() {
		System.out.println("What can we do for you today?");
		System.out.println("1) Make a Withdrawl,");
		System.out.println("2) Make a Deposit,");
		System.out.println("3) Check Balance,");
		System.out.println("4) View History,");
		System.out.println("5) Logout");
	}
}

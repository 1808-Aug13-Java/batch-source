package com.revature.bank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

// TODO: PERSIST DATA BETWEEN LOG IN SESSIONS THEN ASSIGNMENT WILL BE COMPLETE


public class Account {
	
	public static void main(String[] args) {
		System.out.println(emailAddress);
	}
	
	// name with format Firstname Lastname stored when the user creates account
	public String name;
	// email address that is used as a username
	public static String emailAddress;
	// password field
	public static String password;
	// 
	public String deposit;
	public String balance;
	private static Scanner sc = new Scanner(System.in);

	
	// method for printing the sign in/create account menu to a user
	public void newUserMenu() {
		System.out.println("Welcome to Revature Bank!"
				+ "\n1. Sign in"
				+ "\n2. Create an account"
				+ "\nEnter q to quit.");
		
		// begin input prompts
		String checkInput = sc.nextLine();
		switch (checkInput) {
		// display the sign in prompt if user inputs the number '1'
		case "1":
			signIn();
			break;
		// display the account creation prompt if user inputs the number '2'
		case "2":
			printSignUpText();
			loggedInMenu();
			break;
		case "q":
			System.out.println("No worries, you can come back and create an account anytime!");
			sc.close();
			break;
		// if none of the input is not equal to any of the above, recursively call the newUserMenu() method
		default:
			System.out.println("Invalid input. Please try again");
			newUserMenu();
		}
		
		
	}
	
	
	public void printSignUpText() {
		
		System.out.println("Please enter your first and last name separated by a space: ");
		name = sc.nextLine();
		System.out.println("Please enter a valid email address which will be your account username for Revature Bank: ");
		emailAddress = sc.nextLine();
		System.out.println("Your username will be your email address: " + emailAddress);
		System.out.println("Please create a secure password:");
		password = sc.nextLine();
		System.out.println("Account created!");
		
		// our path to the text file
		String path = "src/com/revature/bank/bank_data.txt";
		BufferedWriter bw = null;
		
		
		try {
		// specify the file we want to write to
		File file = new File(path);
		
		// checking first to see if the file exists, creating it if it doesn't
		if (!file.exists()) {
			
			file.createNewFile();
		}
		
		
		FileWriter fw = new FileWriter(file);
		// create output stream
		bw = new BufferedWriter(fw);
		
		// write below to the designated file
		bw.write(name);
		bw.write("\n");
		bw.write(emailAddress);
		bw.write("\n");
		bw.write(password);
		bw.write("\n");
		loggedInMenu();
		bw.write("$" + deposit);
		
		} catch (IOException e) {
				e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loggedInMenu() {
		
		System.out.println("\nMenu"
				+ "\n-----"
				+ "\n1 - Deposit"
				+ "\n2 - Withdraw"
				+ "\n3 - View Balance"
				+ "\n4 - Log Out");
		
		
		switch (sc.nextLine()) {
		// deposit money and place it into .txt file/store it in 'deposit'
		case "1":
			System.out.println("Deposit amount: ");
			deposit = sc.nextLine();
			// display amount of deposit
			System.out.println("You deposited $" + deposit);
			// return to loggedInMenu() after deposit is made
			loggedInMenu();
			break;
		case "2":
			// on input of '2' user can withdraw money
			System.out.println("Withdraw amount: $");
			// define a variable x with state of deposit
			int x = Integer.parseInt(deposit);
			// redefine this variable by removing the desired amount of money. This is the new balance
			x = x - sc.nextInt();
			deposit = Integer.toString(x);
			System.out.println("Your new balance is: $" + x);
			
			// go back to the main logged in menu
			loggedInMenu();
			break;
		case "3":
			// when a user is new, technically the balance is 'null', so we set it to "0"
			// since they have a zero balance, and not the absence of a balance
			if (deposit == null) {
				deposit = "0";
				System.out.println("Your balance is: $" + deposit);
			} else {
				System.out.println("Your balance is: $" + deposit);
			}
			// go back to main logged in menu
			loggedInMenu();
			break;
		case "4":
			newUserMenu();
		}
	}
	
	public void signIn() {
		
		// basic sign in prompt in the console
		System.out.println("Please enter your username: ");
		String email = sc.nextLine();
		System.out.println("Please enter your password: ");
		String inputPassword = sc.nextLine();
		
		try {
			
			// note that lines are 0-indexed using these methods
			// get information from line 2 (where username/email is stored) and assign it to "line"
			String line = Files.readAllLines(Paths.get("src/com/revature/bank/bank_data.txt")).get(1);

			// get information from line 3 (where password is stored) and assign it to "pwLine"
			String pwLine = Files.readAllLines(Paths.get("src/com/revature/bank/bank_data.txt")).get(2);
			// use .equals() since we need to compare equality for two objects, not two primitive types
			// this checks that email account exists and is valid
			if (!line.equals(email)) {
				System.out.println("Invalid login. Please try again");
				signIn();
			}
			
			// validation of log in information
			if (pwLine.equals(inputPassword)) {
				loggedInMenu();
			} else {
				// if password is invalid, prompt for log in info again.
				System.out.println("Invalid password.");
				signIn();
			}
			// basic catch block for IO input/output.
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

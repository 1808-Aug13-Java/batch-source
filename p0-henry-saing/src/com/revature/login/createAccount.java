package com.revature.login;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class createAccount {

	private static Scanner sc = new Scanner(System.in);
//	String LoginAcc = "LoginAccounts.txt";
//	PrintWriter writer = new PrintWriter(LoginAcc);
	
	
	String username;
	String password;
	String passwordCheck;
	
	public void creation() {
		genUsername();
		askPassword();
		// check if username already exists;
		writeUsername();
		writePassword();
		inputBankInfo();
		
		//write username and password into a textfile
	}
	
	public void askUsername() {
		System.out.println("What would you like your Username to be?");
		username = sc.nextLine();
	}
	
	public void askPassword() {
		System.out.println("Please input a password");
		password = sc.nextLine();
		System.out.println("Enter password again to confirm");
		passwordCheck = sc.nextLine();
		if (passwordCheck.equals(password)) {
			System.out.println("Password Confirmed");
		}
		else {
			System.out.println("Password does not match please redo password");
			askPassword();
		}
		
	}
	public void writeUsername() {
		try {
			FileWriter writer = new FileWriter("src\\com\\revature\\login\\LoginAccounts.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(username + "\n");
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void writePassword() {
		try {
			FileWriter writer = new FileWriter("src\\com\\revature\\login\\LoginPasswords.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(password + "\n");
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void usernameCheck() {
		
		File file = new File("src\\com\\revature\\login\\LoginAccounts.txt");
	    try {

	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) {
	            String i = sc.nextLine();
	            if (i.equals(username)) {
	            	System.out.println("This username is already taken please try again.");
	            	genUsername();
	            }
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public void genUsername() {
		askUsername();
		usernameCheck();
	}
	public void inputBankInfo() {
		String bankInfo = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("How much money are you going to put into your account? (Whole Numbers Only)");
		bankInfo += sc.nextLine();
		writeBankInfo(bankInfo);
		sc.close();
	}
	public String writeBankInfo(String data) {
		try {
			FileWriter writer = new FileWriter("src\\com\\revature\\login\\accountInfo.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(data + "\n");
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}

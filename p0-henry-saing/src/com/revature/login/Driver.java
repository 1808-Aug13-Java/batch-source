package com.revature.login;

import java.util.Scanner;

public class Driver {
	
	/*
	 * The accounts and passwords are stored in "LoginAccounts.txt" and "LoginPasswords.txt"
	 * The pairing between accounts and passwords is based on their line position in their
	 * respective files. Username on line 1 has a password of the first line in the passwords
	 * text file.
	 */
	
	
	static String action = "";
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Login startup = new Login();
		UserStart startup1 = new UserStart();
		action = startup1.introText();
		startup.askPrompt(action);
		sc.close();
	}
}

package com.revature.login;

import java.util.Scanner;

public class UserStart {
	
	public String introText() {
		String response = "";
		System.out.println("Welcome to Henry's Bank");
		System.out.println("Would you like to login or create new account?");
		System.out.println("Valid input: \"login\" or \"create\"");
		
		Scanner sc = new Scanner(System.in);
		
		String input= sc.nextLine();
		
		switch(input) {
		case "login":
			response = "login";
			break;
		case "create":
			response = "create";
//			creation.creation();
			break;
		default:
			System.out.println("Invalid Input");
		}
		
		return response;
	}

}

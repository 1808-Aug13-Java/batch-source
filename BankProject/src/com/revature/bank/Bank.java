package com.revature.bank;

import java.util.Scanner;

public class Bank {
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		welcomeMessage();
		
		String in = sc.nextLine();
		
		switch(in) {
		case "L":
			System.out.println();
			break;
		case "C":
			System.out.println();
			break;
		case "E":
			System.out.println();
			break;
		default:
			System.out.println("Please enter a valid input\n");
		}
		
		displayDashboard();

	}
	
	public static void welcomeMessage() {
		System.out.println("Welcome to A-Bank.\n\nPlease select an option:\n\n"
				+ "L - Login\nC - Create account\nE - Exit\n");
	}
	
	public static void displayDashboard() {
		System.out.println("Please select an option:\n\n"
				+ "D - Deposit money\nW - Withdraw funds\nL - Logout\n"
				+ "V - View Balance\n");
	}

}

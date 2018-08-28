package com.revature.bank;

import java.util.Scanner;

public class Bank {
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Account a = new Account();
		boolean exit = false;
		while(!exit) {
			
			welcomeMessage();
			
			String in = sc.nextLine();
			
			switch(in) {
			case "L":
				System.out.println();
				System.out.println("Enter your username: ");
				String username = sc.nextLine();
				System.out.println("Enter your password: ");
				String password = sc.nextLine();
				boolean logout = a.login(username, password);
				while(!logout)
				{
					displayDashboard();
					
					in = sc.nextLine();
					
					switch(in) {
					case "L":
						a.Logout();
						logout = true;
						System.out.println("\nYou have logged out.\n");
						
						break;
					case "D":
						double funds;
						System.out.println("Please enter a deposit amount: ");
						funds = Double.parseDouble(sc.nextLine());
						a.makeDeposit(funds);
						a.displayBalance();
		
						break;
					case "V":
						a.displayBalance();
						break;
					case "W":
						double withdrawal;
						System.out.println("Please enter a withdrawal amount: ");
						withdrawal = Double.parseDouble(sc.nextLine());
						a.makeWithdrawal(withdrawal);
						break;
					default:
						System.out.println("Please enter a valid input\n");
					}
				}
				break;
			case "C":
				System.out.println("Please enter a new username: ");
				String newusername = sc.nextLine(); 
				System.out.println("Please enter a new username: ");
				String newpassword = sc.nextLine(); 
				System.out.println("Please enter a starting balance: ");
				Double startingbalance = Double.parseDouble(sc.nextLine()); 
				a.createAccount(newusername, newpassword, startingbalance);
				System.out.println();
				break;
			case "E":
				exit = true;
				break;
			default:
				System.out.println("Please enter a valid input\n");
			}
		}
		

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

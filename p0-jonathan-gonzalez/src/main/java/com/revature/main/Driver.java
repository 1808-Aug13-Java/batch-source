package com.revature.main;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.revature.dao.BankDaoImpl;
import com.revature.model.Account;

public class Driver {
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		DecimalFormat format = new DecimalFormat("0.00");
		
		BankDaoImpl bdi = new BankDaoImpl();
//		//bdi.createAccount("username2", "password2", 100.25);
//		Account a = bdi.login("username2","password2");
//		System.out.println(a);
		
		//Account a = new Account();
		boolean exit = false;
		while(!exit) {
			
			welcomeMessage();
			
			String in = (sc.nextLine()).toLowerCase();
			
			switch(in) {
			case "l":
				System.out.println();
				System.out.println("Enter your username: ");
				String username = sc.nextLine();
				System.out.println("Enter your password: ");
				String password = sc.nextLine();
				//boolean logout = a.login(username, password);
				boolean logout = false;
				Account a = bdi.login(username, password);
				while(!logout)
				{
					displayDashboard();
					
					in = sc.nextLine();
					
					switch(in) {
					case "l":
						//a.Logout();
						logout = true;
						System.out.println("\nYou have logged out.\n");
						sc.nextLine();
						
						break;
					case "d":
						double funds;
						System.out.println("Please enter a deposit amount: ");
						a.makeDeposit(Double.parseDouble(sc.nextLine()));
						bdi.updateAccount(a);
						sc.nextLine();
//						a.displayBalance();
		
						break;
					case "v":
						System.out.println("Your current balance is $" + format.format(a.getBalance()) + ".\n");
						sc.nextLine();
						break;
					case "w":
						System.out.println("Please enter a withdrawal amount: ");
						a.makeWithdrawal(Double.parseDouble(sc.nextLine()));
						bdi.updateAccount(a);
						sc.nextLine();
						break;
					default:
						System.out.println("Please enter a valid input\n");
					}
				}
				break;
			case "c":
				System.out.println("Please enter a new username: ");
				String newusername = sc.nextLine(); 
				System.out.println("Please enter a new username: ");
				String newpassword = sc.nextLine(); 
				System.out.println("Please enter a starting balance: ");
				Double startingbalance = Double.parseDouble(sc.nextLine()); 
				bdi.createAccount(newusername, newpassword, startingbalance);
				System.out.println();
				break;
			case "e":
				exit = true;
				break;
			default:
				System.out.println("Please enter a valid input\n");
				sc.nextLine();
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

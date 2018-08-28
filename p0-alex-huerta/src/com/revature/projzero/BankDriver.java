package com.revature.projzero;

import java.util.Scanner;



class BankDriver {
	public static void main(String[] args) {
		
		PrimaryAccount objUser = new PrimaryAccount("123","123");
		objUser.mainMenu();
			
	}

}

class PrimaryAccount {
	int balance;
	String userName;
	String userPass;
	
	PrimaryAccount(String uName, String uPass) {
		userName = uName;
		userPass = uPass;
	}
	
	void mainMenu() {
		char input = '\0';
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome");
		System.out.println("Your username is " + userName);
		System.out.println("Your password is " + userPass);
		System.out.println("1. Check total balance ");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Log Out");
		
		do {
			System.out.println("Please choose an option");
			input = scanner.next().charAt(0);
			
			switch(input) {
			
			case '1':
				System.out.println("\n");
				System.out.println("Balance ="+ balance);
				break;
				
			case '2':
				System.out.println("\n");
				System.out.println("How much would you like to deposit?");
				int incoming = scanner.nextInt();
				deposit(incoming);
				break;
				
			case '3':
				System.out.println("\n");
				System.out.println("How much would you like to withdraw?");
				int outgoing = scanner.nextInt();
				withdraw(outgoing);
				break;
				
			case '4':
				System.out.println("\n");
				System.out.println("You are now logged off");
				System.out.println("Thank you for using out services");
				break;
				
			default:
				System.out.println("That was not a valid option. Please try again.");
				break;
				
			}
	}
		while(input != '5');
		
		System.out.println("You are now logged off thank you for using our service.");
		
		
		
	}

	
	void deposit(int outgoing) {
		if(outgoing != 0) {
			balance = balance + outgoing;
		}
	}
	
	void withdraw(int outgoing) {
		if(outgoing != 0) {
			balance = balance - outgoing;
		}else {
				System.err.println("Insufficient Funds");
		}
	}
}
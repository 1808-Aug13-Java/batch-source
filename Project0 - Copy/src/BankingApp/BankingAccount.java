package BankingApp;

import java.util.Scanner;

public class BankingAccount extends UserInfo {
	
	String userName;
	int mostRecent;
	int balance;
	
	public BankingAccount() {
		super();
	}

	public BankingAccount(String userName, int mostRecent, int balance) {
		super();
		this.userName = userName;
		this.mostRecent = mostRecent;
		this.balance = balance;
	}

	void deposit (int depositAmount) {
		
		if(depositAmount != 0) {
			balance += depositAmount;
			mostRecent = depositAmount;
		}
	}
	
	void withdraw (int withdrawAmount) {
		
		if(withdrawAmount != 0) {
			balance -= withdrawAmount;
			mostRecent = -withdrawAmount;
		}
	}	
	
	void accountMenu() {
		
		char choice;
		Scanner choiceIn = new Scanner(System.in);
		int depositAmount = 0;
		int withdrawAmount = 0;
		
		System.out.println("Welcome, " +userName);
		System.out.println();
		System.out.println("A: View balance");
		System.out.println("B: Deposit");
		System.out.println("C: Withdraw");
		System.out.println("D: Exit");
		
		writeData("Initial balance:");
		writeData(balance);
		
		do {
			
			System.out.println("Please input an option from the menu");
			choice = Character.toUpperCase(choiceIn.next().charAt(0));
						
			switch(choice) {
			
			case 'A': 
				System.out.println("Balance: " + balance);
				break;
			
			case 'B': 
				
				System.out.println("Deposit amount: ");
				depositAmount = choiceIn.nextInt();
				deposit(depositAmount);
				break;
			
			case 'C':
				System.out.println("Withdrawal amount: ");
				withdrawAmount = choiceIn.nextInt();
				withdraw(withdrawAmount);
				break;
				
			case 'D': 
				System.out.println("Thank you! A record of your login and " 
						+ "transactions can be found in userdata.txt");
				break;
			
			default: 
				System.out.println("You must enter a choice from the menu");
			}
			
		} while(choice != 'D');
		
		writeData("Balance: ");
		writeData(balance);
		writeData("Deposits:");
		writeData(depositAmount);
		writeData("Withdrawals:");
		writeData(withdrawAmount);

	}
	
}



package com.revature.bank;

public class User {
	
	private String username;
	private String password;
	private int balance;
	
	public void makeDeposit(int funds) {
		this.balance = this.balance + funds;
		System.out.println("Your new balance is $" + balance + ".\n");
		
	}
	
	public void makeWithdrawal(int funds) {
		if(this.balance >= funds) {
			this.balance = this.balance - funds;
			System.out.println("Your new balance is $" + balance + ".\n");
		}
		else {
			System.out.println("You don't have enough funds.\n");
		}
	}
	
	public void displayBalance() {
		System.out.println("Your current balance is $" + balance + ".\n");
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		//System.out.println("Please enter your username:");
		return username;
	}
	
	public String getPassword() {
		//System.out.println("Please enter your password:");
		return password;
	}

}

package com.revature.banking;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankAccount implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9168396640268829377L;
	private static Scanner sc = new Scanner(System.in);
	private int balance = 0;
	
	public BankAccount(int balance, User user) {
		super();
		this.balance = balance;
	}
	
	public void deposit() {
		double amount = getPositiveDouble();
		// not authenticating because who doesn't like free money
		if(amount <= 0) {
			System.out.println("Can't deposit negative amount or zero");
		} else {
			this.balance += amount;
		}
	}
	
	public void withdraw() {
		double amount = getPositiveDouble();
		if(amount <= 0) {
			System.out.println("Can't withdraw a negative amount or zero");
		} else {
			this.balance -= amount;
			System.out.println("After withdrawing " + amount +
					"your amount is $" + this.balance);
		}
	}

	public BankAccount() {
		super();
	}
	
	@Override
	public String toString() {
		return "BankAccount [balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + balance;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		if (balance != other.balance)
			return false;
		return true;
	}

	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		
		this.balance = balance;
	}
	
	private double getPositiveDouble() {
		double amount = 0;
		
		do {
			try {
				amount = Double.parseDouble(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Enter a valid number");
			}
		} while ( amount == 0 );
		
		
		return amount;
	}

	
	
}

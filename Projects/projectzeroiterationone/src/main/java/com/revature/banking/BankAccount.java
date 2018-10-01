package com.revature.banking;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class BankAccount implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9168396640268829377L;
	private static Scanner sc = new Scanner(System.in);
	private float balance = 0;
	final static Logger logger = Logger.getLogger(BankAccount.class);
	
	public BankAccount(int balance, User user) {
		super();
		this.balance = balance;
	}
	
	public void deposit() {
		double amount = getPositiveDouble();
		// not authenticating because who doesn't like free money
		if(amount <= 0) {
			logger.info("Can't deposit negative amount or zero");
		} else {
			this.balance += amount;
		}
	}
	
	public void withdraw() {
		double amount = getPositiveDouble();
		if(amount <= 0 || this.balance - amount < 0) {
			logger.info("Withdraw declined");
			logger.info("");
		} else {
			this.balance -= amount;
			logger.info("After withdrawing $" + amount +
					" your amount is $" + this.balance);
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
		result = (int) (prime * result + balance);
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

	public float getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		
		this.balance = balance;
	}
	
	private double getPositiveDouble() {
		double amount = 0;
		logger.info("Enter a valid number");
		do {
			try {
				
				amount = Double.parseDouble(sc.nextLine());
			} catch (Exception e) {
				logger.info("Enter a valid number");
			}
		} while ( amount == 0 );
		
		
		return amount;
	}

	
	
}

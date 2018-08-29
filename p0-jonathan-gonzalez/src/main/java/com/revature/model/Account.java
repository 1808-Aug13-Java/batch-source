package com.revature.model;

import java.text.DecimalFormat;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.exceptions.*;

public class Account {
	
	private String username;
	private String password;
	private double balance;
	private static Scanner sc = new Scanner(System.in);
	DecimalFormat format = new DecimalFormat("0.00");
	
	public Account(String username, String password, double balance) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
	
	static final Logger logger = Logger.getLogger(Account.class);
	
	public void makeDeposit() {
		
		logger.info("Please enter a deposit amount: ");
		String amount = sc.nextLine();
		
		try {
			makeDeposit(amount);
			logger.info("Your new balance is $" + format.format(balance) + ".\n");

		}
		catch(FractionalCentException e) {
			logger.error("Can't deposit fractional cents.\n");
		}
		catch(NegativeNumberException e) {
			logger.error("Can't deposit a negative amount.\n");
		}
		catch(IllegalArgumentException e) {
			logger.error("Please enter a number.\n");
		}

		
	}
	
	public double makeDeposit(String amount) throws NegativeNumberException, FractionalCentException {
		
		double parsedAmount = Double.parseDouble(amount);
		double delta = Math.abs(parsedAmount - ((int) parsedAmount));
		if(parsedAmount < 0) {
			throw new NegativeNumberException();
		}
		else if(delta < 0.0099999999 & delta > 0) {
			throw new FractionalCentException();
		}
		else
		{
			balance = balance + parsedAmount;
		}
		
		return balance;
	}
	
	public void makeWithdrawal() {

				logger.info("Please enter a withdrawal amount: ");
				String amount = sc.nextLine();
				try {
					makeWithdrawal(amount);
					logger.info("Your balance is $" + format.format(balance) + ".\n");

				}
				catch(FractionalCentException e) {
					logger.error("Can't withdraw fractional cents.\n");

				}
				catch(NegativeNumberException e) {
					logger.error("Can't withdraw a negative amount.\n");

				}
				catch(IllegalArgumentException e) {
					logger.error("Please enter a number.\n");

				}
				
	
		
	}
	
	public double makeWithdrawal(String amount) throws NegativeNumberException, FractionalCentException {

			double parsedAmount = Double.parseDouble(amount);
			double delta = parsedAmount%1;
			if(parsedAmount < 0) {
				throw new NegativeNumberException();
			}
			else if(delta < 0.0099999999 & delta > 0) {
				throw new FractionalCentException();
			}
			else if(balance < parsedAmount) {
				logger.info("You don't have enough funds.\n");

			}
			else
			{
				balance = balance - parsedAmount;
			}
	
	return balance;
	
}
	
	public Account() {
		super();
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	
	public void viewBalance() {
		logger.info("Your current balance is $" + format.format(balance) + ".\n");

	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", balance=" + balance + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Account other = (Account) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}

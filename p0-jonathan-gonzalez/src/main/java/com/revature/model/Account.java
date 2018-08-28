package com.revature.model;

public class Account {
	
	private String username;
	private String password;
	private double balance;
	
	public void makeDeposit(double funds) {
		balance = balance + funds;
		System.out.println("Your new balance is $" + balance + ".\n");
		
	}
	
	public void makeWithdrawal(double funds) {
		if(balance >= funds) {
			balance = balance - funds;
			System.out.println("Your new balance is $" + balance + ".\n");
		}
		else {
			System.out.println("You don't have enough funds.\n");
		}
	}
	
//	public void logout() {
//		
//	}
//	public void viewBalance() {
//	}
	
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

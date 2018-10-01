package com.revature.models;

public class Account {
	
	private int accountId;
	private int customerId;
	private double balance;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int accountId, int customerId, double balance) {
		super();
		this.accountId = accountId;
		this.customerId = customerId;
		this.balance = balance;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + customerId;
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
		if (accountId != other.accountId)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (customerId != other.customerId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", customerId=" + customerId + ", balance=" + balance + "]";
	}
	

}

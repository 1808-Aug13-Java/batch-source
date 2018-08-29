package com.revature.model;

public class Accounts {
	private int accountId=0;
	private float balance;
	/**
	 * 
	 */
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param accountId
	 * @param balance
	 */
	public Accounts(int accountId, float balance) {
		super();
		this.accountId = accountId;
		this.balance = balance;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + Float.floatToIntBits(balance);
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
		Accounts other = (Accounts) obj;
		if (accountId != other.accountId)
			return false;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Accounts [accountId=" + accountId + ", balance=" + balance + "]";
	}
	
	
	
}

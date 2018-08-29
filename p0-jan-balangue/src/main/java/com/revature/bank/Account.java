package com.revature.bank;

public class Account {
	
	private int accountId;
	private String accountType;
	private double balance;
	private int customerId;
	
	public Account() {
		super();
	}

	public Account(String accountType, double balance, int customerId) {
		super();
		this.accountType = accountType;
		this.balance = balance;
		this.customerId = customerId;
	}

	public int getAccountId() {
		return accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance(int accountId) {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getCustomerId() {
		return customerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + customerId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Account other = (Account) obj;
		if (accountId != other.accountId) {
			return false;
		}
		if (accountType == null) {
			if (other.accountType != null) {
				return false;
			}
		} else if (!accountType.equals(other.accountType)) {
			return false;
		}
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance)) {
			return false;
		}
		if (customerId != other.customerId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountType=" + accountType + ", balance=" + balance
				+ ", customerId=" + customerId + "]";
	}

}

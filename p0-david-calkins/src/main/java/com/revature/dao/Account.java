package com.revature.dao;

import java.math.BigDecimal;

/** 
 * Represents the data that would be held in a banking account. 
 */
public class Account {
	/** The unique id of this account. */
	private long accId;
	
	/** The type of this account. */
	private String accType;
	
	/** The amount of funds in this account. */
	private BigDecimal balance;
	
	/** Constructs an empty account object. */
	public Account() {}
	
	/** Constructors a new account object with the specified parameters. */
	public Account(long accId, String accType, BigDecimal balance) {
		super();
		this.accId = accId;
		this.accType = accType;
		this.balance = balance;
	}

	public long getAccId() {
		return accId;
	}

	public void setAccId(long accId) {
		this.accId = accId;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accId ^ (accId >>> 32));
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
		if (accId != other.accId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accId=" + accId + ", accType=" + accType + ", balance=" + balance + "]";
	}

	
}

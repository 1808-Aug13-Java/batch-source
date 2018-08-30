package com.revature.model;

import java.sql.Date;

public class Transaction {

	private int userId;
	private int transactionId;
	private String transactionType;
	private float amount;
	private Date transactionDate;
	private int fromUserId;
	
	public Transaction() {
		super();
	}

	public Transaction(int userId, int transactionId, String transactionType, float amount, Date transactionDate,
			int fromUserId) {
		super();
		this.userId = userId;
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.fromUserId = fromUserId;
	}

	public int getfromUserId() {
		return fromUserId;
	}

	public void setfromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}

	public int getuserId() {
		return userId;
	}

	public void setuserId(int userId) {
		this.userId = userId;
	}

	public int gettransactionId() {
		return transactionId;
	}

	public void settransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String gettransactionType() {
		return transactionType;
	}

	public void settransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date gettransactionDate() {
		return transactionDate;
	}

	public void settransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + transactionId;
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		result = prime * result + userId;
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
		Transaction other = (Transaction) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionId != other.transactionId)
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [userId=" + userId + ", transactionId=" + transactionId + ", transactionType="
				+ transactionType + ", amount=" + amount + ", transactionDate=" + transactionDate + "]";
	}
	
	
	

}

package com.revature.project0.abstraction;

public class Transaction {

	private int transactionID;
	private String type; //WITH,DEPT
	private int accountFrom;
	private int accountTo;
	private float amount;

	public Transaction() {
		super();
	}
	public Transaction(int transactionID, String type, int accountFrom, int accountTo, float amount) {
		super();
		this.transactionID = transactionID;
		this.type = type;
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountFrom;
		result = prime * result + accountTo;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + transactionID;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}
	public int getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
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
		if (accountFrom != other.accountFrom)
			return false;
		if (accountTo != other.accountTo)
			return false;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (transactionID != other.transactionID)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", type=" + type + ", accountFrom=" + accountFrom
				+ ", accountTo=" + accountTo + ", amount=" + amount + "]";
	}	
}

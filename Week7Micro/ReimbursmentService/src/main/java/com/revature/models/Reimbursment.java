package com.revature.models;

public class Reimbursment {
	
	private int reimbursmentId;
	private int customerId;
	private double amount;
	
	public Reimbursment() {
		super();
	}
	
	public Reimbursment(int reimbursmentId, int customerId, double amount) {
		super();
		this.reimbursmentId = reimbursmentId;
		this.customerId = customerId;
		this.amount = amount;
	}

	public int getReimbursmentId() {
		return reimbursmentId;
	}

	public void setReimbursmentId(int reimbursmentId) {
		this.reimbursmentId = reimbursmentId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + customerId;
		result = prime * result + reimbursmentId;
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
		Reimbursment other = (Reimbursment) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (customerId != other.customerId)
			return false;
		if (reimbursmentId != other.reimbursmentId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursment [reimbursmentId=" + reimbursmentId + ", customerId=" + customerId + ", amount=" + amount
				+ "]";
	}
	
	
}

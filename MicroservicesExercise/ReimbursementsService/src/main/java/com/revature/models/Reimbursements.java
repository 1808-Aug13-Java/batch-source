package com.revature.models;

public class Reimbursements {

	private int reimbursementsId;
	private int customerId;
	private double amount;
	public Reimbursements() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursements(int reimbursementsId, int customerId, double amount) {
		super();
		this.reimbursementsId = reimbursementsId;
		this.customerId = customerId;
		this.amount = amount;
	}
	public int getReimbursementsId() {
		return reimbursementsId;
	}
	public void setReimbursementsId(int reimbursementsId) {
		this.reimbursementsId = reimbursementsId;
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
		result = prime * result + reimbursementsId;
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
		Reimbursements other = (Reimbursements) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (customerId != other.customerId)
			return false;
		if (reimbursementsId != other.reimbursementsId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursements [reimbursementsId=" + reimbursementsId + ", customerId=" + customerId + ", amount="
				+ amount + "]";
	}
	
	
}

package com.chandrika.models;

public class Reimbursement {
	private int reimbursementId;
	private int employeeId;
	private String reason;
	private double amount;
	public Reimbursement() {
		super();
	}
	public Reimbursement(int reimbursementId, int employeeId, String reason, double amount) {
		super();
		this.reimbursementId = reimbursementId;
		this.employeeId = employeeId;
		this.reason = reason;
		this.amount = amount;
	}
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
		result = prime * result + employeeId;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + reimbursementId;
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason)) {
			return false;
		}
		return (reimbursementId != other.reimbursementId);
	}
	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", employeeId=" + employeeId + ", reason=" + reason
				+ ", amount=" + amount + "]";
	}
	
	
}

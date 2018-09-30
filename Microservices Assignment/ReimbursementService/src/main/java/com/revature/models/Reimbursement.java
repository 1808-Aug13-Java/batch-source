package com.revature.models;

public class Reimbursement {
	
	private int reimbursementId;
	private int employeeId;
	private double amount;
	private String reason;
	private boolean isApproved;
	private int assignedManager;
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int reimbursementId, int employeeId, double amount, String reason, boolean isApproved,
			int assignedManager) {
		super();
		this.reimbursementId = reimbursementId;
		this.employeeId = employeeId;
		this.amount = amount;
		this.reason = reason;
		this.isApproved = isApproved;
		this.assignedManager = assignedManager;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	public int getAssignedManager() {
		return assignedManager;
	}
	public void setAssignedManager(int assignedManager) {
		this.assignedManager = assignedManager;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + assignedManager;
		result = prime * result + employeeId;
		result = prime * result + (isApproved ? 1231 : 1237);
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
		if (assignedManager != other.assignedManager)
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (isApproved != other.isApproved)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", employeeId=" + employeeId + ", amount=" + amount
				+ ", reason=" + reason + ", isApproved=" + isApproved + ", assignedManager=" + assignedManager + "]";
	}


}

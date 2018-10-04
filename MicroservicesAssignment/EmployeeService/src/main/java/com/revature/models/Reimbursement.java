package com.revature.models;

public class Reimbursement {
	private int reimburseID;
	private int employeeID;
	private double amount;
	private String reason;
	private String status;
	
	public Reimbursement(int reimburseID, int employeeID, double amount, String reason, String status) {
		super();
		this.reimburseID = reimburseID;
		this.employeeID = employeeID;
		this.amount = amount;
		this.reason = reason;
		this.status = status;
	}
	
	
	public Reimbursement() {
		super();
	}


	public int getReimburseID() {
		return reimburseID;
	}
	public void setReimburseID(int reimburseID) {
		this.reimburseID = reimburseID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Reimbursement [reimburseID=" + reimburseID + ", employeeID=" + employeeID + ", amount=" + amount
				+ ", reason=" + reason + ", status=" + status + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + employeeID;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + reimburseID;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (employeeID != other.employeeID)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (reimburseID != other.reimburseID)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
}

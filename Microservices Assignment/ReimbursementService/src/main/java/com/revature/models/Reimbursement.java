package com.revature.models;

public class Reimbursement {
	
	private int reimbursementId;
	private int employeeId;
	private double total;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbursementId, int employeeId, double total) {
		super();
		this.reimbursementId = reimbursementId;
		this.employeeId = employeeId;
		this.total = total;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		result = prime * result + reimbursementId;
		long temp;
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (employeeId != other.employeeId)
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", employeeId=" + employeeId + ", total=" + total
				+ "]";
	}
	
	

}

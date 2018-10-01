package com.revature.models;

public class Reimbursement {
	private int reimbursementId;
	private int employeeId;
	private String description;
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int reimbursementId, int employeeId, String description) {
		super();
		this.reimbursementId = reimbursementId;
		this.employeeId = employeeId;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		if (employeeId != other.employeeId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", employeeId=" + employeeId + ", description="
				+ description + "]";
	}
	
}

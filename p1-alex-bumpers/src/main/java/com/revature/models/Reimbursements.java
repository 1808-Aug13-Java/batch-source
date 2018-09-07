package com.revature.models;

public class Reimbursements {
	private int reimbursementId;
	private float reimbursementAmount;
	private String reimbursementStatus; // 'APPROVED', 'DENIED', 'PENDING'
	private int managerId;
	private int employeeId;


	public Reimbursements() {
		super();
	}


	public Reimbursements(int reimbursementId, float reimbursementAmount, String reimbursementStatus, int managerId,
			int employeeId) {
		super();
		this.reimbursementId = reimbursementId;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementStatus = reimbursementStatus;
		this.managerId = managerId;
		this.employeeId = employeeId;
	}


	public int getReimbursementId() {
		return reimbursementId;
	}


	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}


	public float getReimbursementAmount() {
		return reimbursementAmount;
	}


	public void setReimbursementAmount(float reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}


	public String getReimbursementStatus() {
		return reimbursementStatus;
	}


	public void setReimbursementStatus(String reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}


	public int getManagerId() {
		return managerId;
	}


	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		result = prime * result + managerId;
		result = prime * result + Float.floatToIntBits(reimbursementAmount);
		result = prime * result + reimbursementId;
		result = prime * result + ((reimbursementStatus == null) ? 0 : reimbursementStatus.hashCode());
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
		if (employeeId != other.employeeId)
			return false;
		if (managerId != other.managerId)
			return false;
		if (Float.floatToIntBits(reimbursementAmount) != Float.floatToIntBits(other.reimbursementAmount))
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		if (reimbursementStatus == null) {
			if (other.reimbursementStatus != null)
				return false;
		} else if (!reimbursementStatus.equals(other.reimbursementStatus))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Reimbursements [reimbursementId=" + reimbursementId + ", reimbursementAmount=" + reimbursementAmount
				+ ", reimbursementStatus=" + reimbursementStatus + ", managerId=" + managerId + ", employeeId="
				+ employeeId + "]";
	}
}
package com.revature.reim.model;

public class Reimbursement {
	private int empId;
	private String status;
	private String resolution;
	private double amount;
	private int manId;
	private int reimId;

	public Reimbursement() {
		super();
	}

	public Reimbursement(int empId, String status, String resolution, double amount, int manId, int reimId) {
		super();
		this.empId = empId;
		this.status = status;
		this.resolution = resolution;
		this.amount = amount;
		this.manId = manId;
		this.reimId = reimId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getManId() {
		return manId;
	}

	public void setManId(int manId) {
		this.manId = manId;
	}

	public int getReimId() {
		return reimId;
	}

	public void setReimId(int reimId) {
		this.reimId = reimId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + empId;
		result = prime * result + manId;
		result = prime * result + reimId;
		result = prime * result + ((resolution == null) ? 0 : resolution.hashCode());
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
		if (empId != other.empId)
			return false;
		if (manId != other.manId)
			return false;
		if (reimId != other.reimId)
			return false;
		if (resolution == null) {
			if (other.resolution != null)
				return false;
		} else if (!resolution.equals(other.resolution))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [empId=" + empId + ", status=" + status + ", resolution=" + resolution + ", amount="
				+ amount + ", manId=" + manId + ", reimId=" + reimId + "]";
	}

}
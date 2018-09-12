package com.revature.model;


public class Reimbursment {

	private int reimbId;
	private int empId;
	private String status;
	private String description;
	private int resolvedBy;
	private String managerName;
	private String empName;

	public Reimbursment() {
		super();
	}

	public Reimbursment(int reimbId, int empId, String status, String description,
			int resolvedBy) {
		super();
		this.reimbId = reimbId;
		this.empId = empId;
		this.status = status;
		this.description = description;
		this.resolvedBy = resolvedBy;
	}

	public int getReimbId() {
		return reimbId;
	}

	public int getEmpId() {
		return empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getResolvedBy() {
		return resolvedBy;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public void setResolvedBy(int resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + empId;
		result = prime * result + reimbId;
		result = prime * result + resolvedBy;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Reimbursment)) {
			return false;
		}
		Reimbursment other = (Reimbursment) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (empId != other.empId) {
			return false;
		}
		if (reimbId != other.reimbId) {
			return false;
		}
		if (resolvedBy != other.resolvedBy) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursment [reimbId=" + reimbId + ", empId=" + empId + ", status=" + status + ", description="
				+ description + ", resolvedBy=" + resolvedBy + "]";
	}

	
}

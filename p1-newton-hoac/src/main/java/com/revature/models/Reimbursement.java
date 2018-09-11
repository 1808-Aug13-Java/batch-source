package com.revature.models;

public class Reimbursement {
	private int RId;
	private int empId;
	private int manId;
	private String status;
	private String action;
	private String description;
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int rId, int empId, int manId, String status, String action, String description) {
		super();
		RId = rId;
		this.empId = empId;
		this.manId = manId;
		this.status = status;
		this.action = action;
		this.description = description;
	}
	public int getRId() {
		return RId;
	}
	public void setRId(int rId) {
		RId = rId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getManId() {
		return manId;
	}
	public void setManId(int manId) {
		this.manId = manId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
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
		result = prime * result + RId;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + empId;
		result = prime * result + manId;
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
		if (RId != other.RId)
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (empId != other.empId)
			return false;
		if (manId != other.manId)
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
		return "Reimbursement [RId=" + RId + ", empId=" + empId + ", manId=" + manId + ", status=" + status
				+ ", action=" + action + ", description=" + description + "]";
	}
	
}

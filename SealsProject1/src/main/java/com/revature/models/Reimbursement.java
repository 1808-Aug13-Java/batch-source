package com.revature.models;

import java.util.Date;

public class Reimbursement {

	private int id;
	private int managerId;
	private int empId;
	private int status;
	private double amount;
	private String description;
	private String managerName;

	public Reimbursement() {
		super();
	}

	public Reimbursement(int id, int managerId, int empId, int status, double amount,
			String description) {
		super();
		this.id = id;
		this.managerId = managerId;
		this.empId = empId;
		this.status = status;
		this.amount = amount;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}


	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	public String getManagerName() {
		return managerName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", managerId=" + managerId + ", empId=" + empId + ", status=" + status
				+ ", amount=" + amount + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + empId;
		result = prime * result + id;
		result = prime * result + managerId;
		result = prime * result + status;
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (empId != other.empId)
			return false;
		if (id != other.id)
			return false;
		if (managerId != other.managerId)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
package com.revature.model;

import java.util.Date;

public class Reimbursment {

	private int reimbId;
	private int empId;
	private String status;
	private Date subDate;
	private Date resolvedDate;
	private String description;
	private int resolvedBy;

	public Reimbursment() {
		super();
	}

	public Reimbursment(int reimbId, int empId, String status, Date subDate, Date resolvedDate, String description,
			int resolvedBy) {
		super();
		this.reimbId = reimbId;
		this.empId = empId;
		this.status = status;
		this.subDate = subDate;
		this.resolvedDate = resolvedDate;
		this.description = description;
		this.resolvedBy = resolvedBy;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
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

	public Date getSubDate() {
		return subDate;
	}

	public void setSubDate(Date subDate) {
		this.subDate = subDate;
	}

	public Date getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(Date resolvedDate) {
		this.resolvedDate = resolvedDate;
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

	public void setResolvedBy(int resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + empId;
		result = prime * result + reimbId;
		result = prime * result + resolvedBy;
		result = prime * result + ((resolvedDate == null) ? 0 : resolvedDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((subDate == null) ? 0 : subDate.hashCode());
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
		Reimbursment other = (Reimbursment) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description)) {
			return false;}
		if (empId != other.empId)
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (resolvedBy != other.resolvedBy)
			return false;
		if (resolvedDate == null) {
			if (other.resolvedDate != null)
				return false;
		} else if (!resolvedDate.equals(other.resolvedDate)) {
			return false;}
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status)) {
			return false;}
		if (subDate == null) {
			if (other.subDate != null)
				return false;
		} else if (!subDate.equals(other.subDate)) {
			return false;}
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursment [reimbId=" + reimbId + ", empId=" + empId + ", status=" + status + ", subDate=" + subDate
				+ ", resolvedDate=" + resolvedDate + ", description=" + description + ", resolvedBy=" + resolvedBy
				+ "]";
	}

}

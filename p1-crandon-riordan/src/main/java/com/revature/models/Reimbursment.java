package com.revature.models;

import java.math.BigDecimal;
import java.sql.Date;

public class Reimbursment {
	private int reimbursmentId;
	private int employeeId;
	private BigDecimal amount;
	private String currentState;
	private int managerId;
	private String reason;
	private String imageUrl;
	private Date dateCreated;
	private Employee employee;
	private Employee manager;
	
	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Employee getManager() {
		return manager;
	}


	public void setManager(Employee manager) {
		this.manager = manager;
	}


	public Reimbursment() {
		super();
	}


	public Reimbursment(int reimbursmentId, int employeeId, BigDecimal amount, String currentState, int managerId,
			String reason, String imageUrl, Date dateCreated) {
		super();
		this.reimbursmentId = reimbursmentId;
		this.employeeId = employeeId;
		this.amount = amount;
		this.currentState = currentState;
		this.managerId = managerId;
		this.reason = reason;
		this.imageUrl = imageUrl;
		this.dateCreated = dateCreated;
	}


	public int getReimbursmentId() {
		return reimbursmentId;
	}


	public void setReimbursmentId(int reimbursmentId) {
		this.reimbursmentId = reimbursmentId;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public String getCurrentState() {
		return currentState;
	}


	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}


	public int getManagerId() {
		return managerId;
	}


	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public Date getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((currentState == null) ? 0 : currentState.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + employeeId;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + managerId;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + reimbursmentId;
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
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (currentState == null) {
			if (other.currentState != null)
				return false;
		} else if (!currentState.equals(other.currentState))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (managerId != other.managerId)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (reimbursmentId != other.reimbursmentId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Reimbursment [reimbursmentId=" + reimbursmentId + ", employeeId=" + employeeId + ", amount=" + amount
				+ ", currentState=" + currentState + ", managerId=" + managerId + ", reason=" + reason + ", imageUrl="
				+ imageUrl + ", dateCreated=" + dateCreated + "]";
	}


	


	
	
	

}

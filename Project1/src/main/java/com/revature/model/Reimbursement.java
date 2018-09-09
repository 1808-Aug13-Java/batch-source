package com.revature.model;

import java.math.BigDecimal;
import java.util.Date;
//import java.util.Date;

public class Reimbursement {
	
	private int id; // request id
	private String reason;
	private String status;
	private BigDecimal amount;
	private Date submitDate;
	private int requestHandlerId; // manager id
	private int empId;
	
	// has-a relationship: each reimbursement HAS an employee
	private Zukemployee employee;

	/**
	 * 
	 */
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	/** (to be able to insert w triggers...)
	 * @param id
	 * @param reason
	 * @param status
	 * @param amount
	 * @param submitDate
	 * @param requestHandlerId
	 * @param empId
	 */
	public Reimbursement(int id, String reason, String status, BigDecimal amount, Date submitDate, int requestHandlerId,
			int empId) {
		super();
		this.id = id;
		this.reason = reason;
		this.status = status;
		this.amount = amount;
		this.submitDate = submitDate;
		this.requestHandlerId = requestHandlerId;
		this.empId = empId;
	}
	
	/**
	 * @param reason
	 * @param status
	 * @param amount
	 * @param requestHandlerId
	 * @param empId
	 */
	public Reimbursement(String reason, String status, BigDecimal amount, int requestHandlerId,
			int empId) {
		super();
		this.reason = reason;
		this.status = status;
		this.amount = amount;
		this.requestHandlerId = requestHandlerId;
		this.empId = empId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the submitDate
	 */
	public Date getSubmitDate() {
		return submitDate;
	}

	/**
	 * @param submitDate the submitDate to set
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	/**
	 * @return the requestHandlerId
	 */
	public int getRequestHandlerId() {
		return requestHandlerId;
	}

	/**
	 * @param requestHandlerId the requestHandlerId to set
	 */
	public void setRequestHandlerId(int requestHandlerId) {
		this.requestHandlerId = requestHandlerId;
	}

	/**
	 * @return the empId
	 */
	public int getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + empId;
		result = prime * result + id;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + requestHandlerId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submitDate == null) ? 0 : submitDate.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (empId != other.empId)
			return false;
		if (id != other.id)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (requestHandlerId != other.requestHandlerId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submitDate == null) {
			if (other.submitDate != null)
				return false;
		} else if (!submitDate.equals(other.submitDate))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", reason=" + reason + ", status=" + status + ", amount=" + amount
				+ ", submitDate=" + submitDate + ", requestHandlerId=" + requestHandlerId + ", empId=" + empId + "]";
	}
	
}

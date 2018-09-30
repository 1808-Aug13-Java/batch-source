package com.revature.models;

import java.util.Date;

public class Reimbursement {
	private long id;
	private long requesterId;
	private String status;
	private double amount;
	private Date submitDate;
	private String description;
	private long resolvedById;
	private String reason;
	private Date resolveDate;
	
	public Reimbursement() {}

	public Reimbursement(long id, long requesterId, String status, double amount, Date submitDate,
			String description, long resolvedById, String reason, Date resolveDate) {
		super();
		this.id = id;
		this.requesterId = requesterId;
		this.status = status;
		this.amount = amount;
		this.submitDate = submitDate;
		this.description = description;
		this.resolvedById = resolvedById;
		this.reason = reason;
		this.resolveDate = resolveDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRequester() {
		return requesterId;
	}

	public void setRequester(long requesterId) {
		this.requesterId = requesterId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getResolvedBy() {
		return resolvedById;
	}

	public void setResolvedBy(long resolvedBy) {
		this.resolvedById = resolvedBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getResolveDate() {
		return resolveDate;
	}

	public void setResolveDate(Date resolveDate) {
		this.resolveDate = resolveDate;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", RequesterId=" + requesterId + ", status=" + status + ", amount=" + amount
				+ ", submitDate=" + submitDate + ", description=" + description + ", resolvedById=" + resolvedById
				+ ", reason=" + reason + ", resolveDate=" + resolveDate + "]";
	}
	
	
}

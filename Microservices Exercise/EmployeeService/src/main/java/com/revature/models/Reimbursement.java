package com.revature.models;

import java.util.Date;

public class Reimbursement {
	private long id;
	private Employee requester;
	private String status;
	private double amount;
	private Date submitDate;
	private String description;
	private Manager resolvedBy;
	private String reason;
	private Date resolveDate;
	
	public Reimbursement() {}

	public Reimbursement(long id, Employee requester, String status, double amount, Date submitDate,
			String description, Manager resolvedBy, String reason, Date resolveDate) {
		super();
		this.id = id;
		this.requester = requester;
		this.status = status;
		this.amount = amount;
		this.submitDate = submitDate;
		this.description = description;
		this.resolvedBy = resolvedBy;
		this.reason = reason;
		this.resolveDate = resolveDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee getRequester() {
		return requester;
	}

	public void setRequester(Employee requester) {
		this.requester = requester;
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

	public Manager getResolvedBy() {
		return resolvedBy;
	}

	public void setResolvedBy(Manager resolvedBy) {
		this.resolvedBy = resolvedBy;
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
		return "Reimbursement [id=" + id + ", Requester=" + requester + ", status=" + status + ", amount=" + amount
				+ ", submitDate=" + submitDate + ", description=" + description + ", resolvedBy=" + resolvedBy
				+ ", reason=" + reason + ", resolveDate=" + resolveDate + "]";
	}
	
	
}

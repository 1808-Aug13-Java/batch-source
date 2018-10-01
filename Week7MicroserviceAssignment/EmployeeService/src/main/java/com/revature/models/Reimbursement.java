package com.revature.models;

import java.math.BigDecimal;

public class Reimbursement {

	private int reimbursementId;
	private int employeeId;
	private BigDecimal amount;
	private String reason;
	private boolean approved;
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + employeeId;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + reimbursementId;
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
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (approved != other.approved)
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", employeeId=" + employeeId + ", amount=" + amount
				+ ", reason=" + reason + ", approved=" + approved + "]";
	}
	public Reimbursement(int reimbursementId, int employeeId, BigDecimal amount, String reason, boolean approved) {
		super();
		this.reimbursementId = reimbursementId;
		this.employeeId = employeeId;
		this.amount = amount;
		this.reason = reason;
		this.approved = approved;
	}
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

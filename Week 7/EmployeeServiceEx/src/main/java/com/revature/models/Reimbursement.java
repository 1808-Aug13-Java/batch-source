package com.revature.models;

public class Reimbursement {
	private long reimId;
	private long accountId;
	private String reason;
	private double amount;
	public Reimbursement() {
		super();
	}
	public Reimbursement(long reimId, long accountId, String reason, double amount) {
		super();
		this.reimId = reimId;
		this.accountId = accountId;
		this.reason = reason;
		this.amount = amount;
	}
	public long getReimId() {
		return reimId;
	}
	public void setReimId(long reimId) {
		this.reimId = reimId;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountId ^ (accountId >>> 32));
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + (int) (reimId ^ (reimId >>> 32));
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
		if (accountId != other.accountId)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (reimId != other.reimId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimId=" + reimId + ", accountId=" + accountId + ", reason=" + reason + ", amount="
				+ amount + "]";
	}
	
	
}
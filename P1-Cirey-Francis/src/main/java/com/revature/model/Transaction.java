package com.revature.model;

import java.sql.Date;

/*
 * Full Transaction Java Bean
 */

public class Transaction {
	
	private int id;
	private Date date;
	private int creator;
	private String approved;
	private String approverName;
	private float amount;
	private String reason;
	
	public Transaction() {
		super();
	}
	
	public Transaction(int id, Date date, int creator, String approved, String approverName, float amount,
			String reason) {
		super();
		this.id = id;
		this.date = date;
		this.creator = creator;
		this.approved = approved;
		this.approverName = approverName;
		this.amount = amount;
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getApproved() {
		return approved;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public String isApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((approved == null) ? 0 : approved.hashCode());
		result = prime * result + ((approverName == null) ? 0 : approverName.hashCode());
		result = prime * result + creator;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
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
		Transaction other = (Transaction) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (approved == null) {
			if (other.approved != null)
				return false;
		} else if (!approved.equals(other.approved))
			return false;
		if (approverName == null) {
			if (other.approverName != null)
				return false;
		} else if (!approverName.equals(other.approverName))
			return false;
		if (creator != other.creator)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", date=" + date + ", creator=" + creator + ", approved=" + approved
				+ ", approverName=" + approverName + ", amount=" + amount + ", reason=" + reason + "]";
	}

}

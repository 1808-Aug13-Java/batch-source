package com.revature.models;

public class Reimbursement {
	private int requestId;
	private int staffId;
	private String status; //'PENDING', 'APPROVED', OR DENIED'
	private int manId;
	private float amount;  //at most 2 decimals after
	private String reqDate;   //date? :O   DEPENDS ON INPUT ON HTML :((((
	private String message; //500 character
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int requestId, int staffId, String status, int manId, float amount, String reqDate,
			String message) {
		super();
		this.requestId = requestId;
		this.staffId = staffId;
		this.status = status;
		this.manId = manId;
		this.amount = amount;
		this.reqDate = reqDate;
		this.message = message;
	}

	public int getRequestId() {
		return requestId;
	}

	public int getStaffId() {
		return staffId;
	}

	public String getStatus() {
		return status;
	}

	public int getManId() {
		return manId;
	}

	public float getAmount() {
		return amount;
	}

	public String getReqDate() {
		return reqDate;
	}

	public String getMessage() {
		return message;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setManId(int manId) {
		this.manId = manId;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + manId;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((reqDate == null) ? 0 : reqDate.hashCode());
		result = prime * result + requestId;
		result = prime * result + staffId;
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
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (manId != other.manId)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (reqDate == null) {
			if (other.reqDate != null)
				return false;
		} else if (!reqDate.equals(other.reqDate))
			return false;
		if (requestId != other.requestId)
			return false;
		if (staffId != other.staffId)
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
		return "Reimbursement [requestId=" + requestId + ", staffId=" + staffId + ", status=" + status + ", manId="
				+ manId + ", amount=" + amount + ", reqDate=" + reqDate + ", message=" + message + "]";
	}
	
	
}

package com.revature.models;

public class Request {

	private int requestID;
	private int empID;
	private double amount;
	private short status;
	private int managerID;
	private String description;
	
	public Request(int requestID, int empID, double amount, short status, int managerID, String description) {
		super();
		this.requestID = requestID;
		this.empID = empID;
		this.amount = amount;
		this.status = status;
		this.managerID = managerID;
		this.description = description;
	}

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + empID;
		result = prime * result + managerID;
		result = prime * result + requestID;
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
		Request other = (Request) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (empID != other.empID)
			return false;
		if (managerID != other.managerID)
			return false;
		if (requestID != other.requestID)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [requestID=" + requestID + ", empID=" + empID + ", amount=" + amount + ", status=" + status
				+ ", managerID=" + managerID + ", description=" + description + "]";
	}
}

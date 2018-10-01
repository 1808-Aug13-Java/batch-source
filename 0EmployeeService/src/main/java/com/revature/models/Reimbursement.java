package com.revature.models;

public class Reimbursement {
	//the regular things, but also inlucde the foreign key referencing the employee id
	private int reimbId;
	private int empId;
	private double amount;
	
	//put a constructor to set all items cuz this example uses no hibernate to map to a table
	public Reimbursement(int rId, int eId, double amount){
		this.reimbId = rId;
		this.empId = eId;
		this.amount = amount;
	}

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", empId=" + empId + ", amount=" + amount + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + empId;
		result = prime * result + reimbId;
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
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (empId != other.empId)
			return false;
		if (reimbId != other.reimbId)
			return false;
		return true;
	}
	
}

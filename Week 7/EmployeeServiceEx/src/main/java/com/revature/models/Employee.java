package com.revature.models;

import java.util.List;

public class Employee {
	
	private long employeeId;
	private String name;
	private List<Reimbursement> reims;
	
	
	public Employee() {
		super();
	}
	
	public Employee(long accountId, String name, List<Reimbursement> reims) {
		super();
		this.employeeId = accountId;
		this.name = name;
		this.reims = reims;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long accountId) {
		this.employeeId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Reimbursement> getReims() {
		return reims;
	}
	public void setReims(List<Reimbursement> reims) {
		this.reims = reims;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (employeeId ^ (employeeId >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((reims == null) ? 0 : reims.hashCode());
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
		Employee other = (Employee) obj;
		if (employeeId != other.employeeId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reims == null) {
			if (other.reims != null)
				return false;
		} else if (!reims.equals(other.reims))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", reims=" + reims + "]";
	}
	
	
	

}

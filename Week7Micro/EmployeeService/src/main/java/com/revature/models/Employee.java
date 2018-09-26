package com.revature.models;

import java.util.List;

public class Employee {
	
	private int employeeId;
	private String name;
	private String email;
	private List<Reimbursment> reimbursments;
	
	
	public Employee() {
		super();
	}


	public Employee(int employeeId, String name, String email, List<Reimbursment> reimbursments) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.email = email;
		this.reimbursments = reimbursments;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Reimbursment> getReimbursments() {
		return reimbursments;
	}


	public void setReimbursments(List<Reimbursment> reimbursments) {
		this.reimbursments = reimbursments;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + employeeId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((reimbursments == null) ? 0 : reimbursments.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeId != other.employeeId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reimbursments == null) {
			if (other.reimbursments != null)
				return false;
		} else if (!reimbursments.equals(other.reimbursments))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", email=" + email + ", reimbursments="
				+ reimbursments + "]";
	}
	
	
}

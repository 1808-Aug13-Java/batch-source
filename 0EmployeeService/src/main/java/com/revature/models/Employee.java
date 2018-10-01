package com.revature.models;

import java.util.List;

public class Employee {
	
	//regular items but include also the list of reimbursement objects
	private int empId;
	private String name;
	private String email;
	private List<Reimbursement> reimbursements;
	public Employee() {
		super();
	}
	public Employee(int empId, String name, String email, List<Reimbursement> reimbursements) {
		super();
		this.empId = empId;
		this.name = name;
		this.email = email;
		this.reimbursements = reimbursements;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", email=" + email + ", reimbursements=" + reimbursements
				+ "]";
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
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
	public List<Reimbursement> getReimbursements() {
		return reimbursements;
	}
	public void setReimbursements(List<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + empId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((reimbursements == null) ? 0 : reimbursements.hashCode());
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
		if (empId != other.empId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reimbursements == null) {
			if (other.reimbursements != null)
				return false;
		} else if (!reimbursements.equals(other.reimbursements))
			return false;
		return true;
	}
	
	
}

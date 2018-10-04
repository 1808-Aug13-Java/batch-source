package com.revature.models;

import java.util.List;

public class Employee {
	private int employeeID;
	private String empName;
	private String role;
	private List<Reimbursement> reimbursements;
	
	public List<Reimbursement> getReimbursements() {
		return reimbursements;
	}


	public void setReimbursements(List<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}


	public int getEmployeeID() {
		return employeeID;
	}
	
	
	public Employee() {
		super();
	}

	public Employee(int employeeID, String empName, String role) {
		super();
		this.employeeID = employeeID;
		this.empName = empName;
		this.role = role;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", empName=" + empName + ", role=" + role + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empName == null) ? 0 : empName.hashCode());
		result = prime * result + employeeID;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		if (empName == null) {
			if (other.empName != null)
				return false;
		} else if (!empName.equals(other.empName))
			return false;
		if (employeeID != other.employeeID)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}	
}

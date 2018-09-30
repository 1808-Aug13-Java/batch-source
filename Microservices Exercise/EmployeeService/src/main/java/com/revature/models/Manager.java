package com.revature.models;


import java.util.List;

public class Manager extends Employee{
	private List<Employee> employees;

	
	public Manager() {}


	public Manager(long id, String name, List<Reimbursement> reimbursements, List<Employee> employees) {
		super(id, name, reimbursements);
		this.employees = employees;
	}


	public List<Employee> getEmployees() {
		return employees;
	}


	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}


	@Override
	public String toString() {
		return "Manager [getId()=" + getId() 
				+ ", getName()=" + getName()
				+ ", getReimbursements()=" + getReimbursements() 
				+ "employees=" + employees + "]";
	}


	
	
	
} // end of Managers

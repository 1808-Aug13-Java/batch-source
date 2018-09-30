package com.revature.models;

import java.util.List;

public class Employee {
	private long id;
	private String name;
	private List<Reimbursement> reimbursements;
	
	
	public Employee() {}
	
	public Employee(long id, String name, List<Reimbursement> reimbursements) {
		super();
		this.id = id;
		this.name = name;
		this.reimbursements = reimbursements;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Reimbursement> getReimbursements() {
		return reimbursements;
	}

	public void setReimbursements(List<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", reimbursements=" + reimbursements + "]";
	}

	

	
	
	
} // end of class Employee

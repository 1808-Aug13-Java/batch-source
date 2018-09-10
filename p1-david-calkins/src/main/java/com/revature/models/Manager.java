package com.revature.models;

import java.util.Arrays;

public class Manager extends Employee{
	private Employee[] employees;

	
	public Manager() {}
	
	public Manager(long id, String name, String username, String passwordHash, Employee[] employees) {
		super(id, name, username, passwordHash);
		this.employees = employees;
	}

	public Employee[] getEmployees() {
		return employees;
	}

	public void setEmployees(Employee[] employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Manager [employees=" + Arrays.toString(employees) 
				+ ", getId()=" + getId() 
				+ ", getName()=" + getName()
				+ ", getUsername()=" + getUsername() 
				+ ", getPasswordHash()=" + getPasswordHash() + "]";
	}
	
	
} // end of Managers

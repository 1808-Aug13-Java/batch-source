package com.revature.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager extends Employee{
	private List<Employee> employees;

	
	public Manager() {}


	public Manager(long id, String name, String username, String passwordHash, List<Employee> employees) {
		super(id, name, username, passwordHash);
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
		return "Manager [getPasswordHash()=" + getPasswordHash()
				+ ", getId()=" + getId()
				+ ", getName()=" + getName()
				+ ", getUsername()=" + getUsername()
				+ ", employees=" + employees
				+ "]";
	}
	
} // end of Managers

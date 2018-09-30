package com.revature.models;

public class Employee {
	private long id;
	private String name;
	private String username;
	private String passwordHash;
	
	
	public Employee() {}
	
	public Employee(long id, String name, String username, String passwordHash) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.passwordHash = passwordHash;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", username=" 
				+ username + ", passwordHash=" + passwordHash
				+ "]";
	}
	
	
} // end of class Employee

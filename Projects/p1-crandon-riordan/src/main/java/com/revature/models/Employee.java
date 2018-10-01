package com.revature.models;

public class Employee {
	
	private int id;
	private String name;
	private String username;
	private String email;
	private String password;
	private int managerId;
	private int isManager; // assumes 0 false, 1 true;
	private Employee manager;
	
	public Employee() {
		super();
	}
	
	


	




	public Employee(int id, String name, String username, String email, String password, 
			int managerId, int isManager) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.managerId = managerId;
		this.isManager = isManager;
	}
	
	public Employee getManager() {
		return manager;
	}




	public void setManager(Employee manager) {
		this.manager = manager;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getManagerId() {
		return managerId;
	}


	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}


	public int getIsManager() {
		return isManager;
	}


	public void setIsManager(int isManager) {
		this.isManager = isManager;
	}




	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", password="
				+ password + ", managerId=" + managerId + ", isManager=" + isManager + "]";
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + isManager;
		result = prime * result + managerId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (id != other.id)
			return false;
		if (isManager != other.isManager)
			return false;
		if (managerId != other.managerId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
	

	
	
}

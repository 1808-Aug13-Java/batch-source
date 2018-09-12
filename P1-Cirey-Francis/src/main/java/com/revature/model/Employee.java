package com.revature.model;

import java.sql.Date;

/*
 *  Full Employee Java Bean 
 */

public class Employee {
	
	private int id;
	private String name;
	private String email;
	private String username;
	private String privateInfo;
	private Date startDate;
	private float monthlySalary;
	private String isManager;
	
	public Employee() {
		super();
	}

	public Employee(int id, String name, String email, String username, String privateInfo, Date startDate,
			float monthlySalary, String isManager) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.privateInfo = privateInfo;
		this.startDate = startDate;
		this.monthlySalary = monthlySalary;
		this.isManager = isManager;
	}

	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public float getMonthlySalary() {
		return monthlySalary;
	}


	public void setMonthlySalary(float monthlySalary) {
		this.monthlySalary = monthlySalary;
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

	public String getPrivateInfo() {
		return privateInfo;
	}

	public void setPrivateInfo(String privateInfo) {
		this.privateInfo = privateInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((isManager == null) ? 0 : isManager.hashCode());
		result = prime * result + Float.floatToIntBits(monthlySalary);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((privateInfo == null) ? 0 : privateInfo.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		if (isManager == null) {
			if (other.isManager != null)
				return false;
		} else if (!isManager.equals(other.isManager))
			return false;
		if (Float.floatToIntBits(monthlySalary) != Float.floatToIntBits(other.monthlySalary))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (privateInfo == null) {
			if (other.privateInfo != null)
				return false;
		} else if (!privateInfo.equals(other.privateInfo))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", username=" + username
				+ ", privateInfo=" + privateInfo + ", startDate=" + startDate + ", monthlySalary=" + monthlySalary
				+ ", isManager=" + isManager + "]";
	}

	
	
	
	
	
	
	
	

}

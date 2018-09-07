package com.revature.reim.model;

public class Employee {

	private int employeeID;
	private String firstName;
	private String lastName;
	private String email;
	private String e_key;
	private int isMan;

	public Employee() {
		super();
	}
	
	public Employee(int employeeID, String firstName, String lastName, String email, String e_key,int isMan) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.e_key = e_key;
		this.isMan=isMan;
	}



	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", e_key=" + e_key + ", isMan=" + isMan + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((e_key == null) ? 0 : e_key.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + employeeID;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + isMan;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		if (e_key == null) {
			if (other.e_key != null)
				return false;
		} else if (!e_key.equals(other.e_key))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeID != other.employeeID)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isMan != other.isMan)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	public int getIsMan() {
		return isMan;
	}

	public void setIsMan(int isMan) {
		this.isMan = isMan;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getE_key() {
		return e_key;
	}

	public void setE_key(String e_key) {
		this.e_key = e_key;
	}

	
}

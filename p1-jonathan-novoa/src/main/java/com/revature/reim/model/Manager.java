package com.revature.reim.model;

public class Manager {

	private int managerID;
	private String firstName;
	private String lastName;
	private String email;
	private String m_key;
	
	public Manager() {
		super();
	}
	
	public Manager(int managerID, String firstName, String lastName, String email, String m_key) {
		super();
		this.managerID = managerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.m_key = m_key;
	}
	
	public int getManagerID() {
		return managerID;
	}
	public void setManagerID(int managerID) {
		this.managerID = managerID;
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
	public String getM_key() {
		return m_key;
	}
	public void setM_key(String m_key) {
		this.m_key = m_key;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((m_key == null) ? 0 : m_key.hashCode());
		result = prime * result + managerID;
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
		Manager other = (Manager) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (m_key == null) {
			if (other.m_key != null)
				return false;
		} else if (!m_key.equals(other.m_key))
			return false;
		if (managerID != other.managerID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Manager [managerID=" + managerID + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", m_key=" + m_key + "]";
	}
	
}

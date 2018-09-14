package com.revature.models;

public class Employee {

	private int empID;
	private String username;
	private String pass;
	private String email;
	private String fName;
	private String lName;
	private long phone;
	private long secondary;
	private String stateAbbr;
	private boolean manager;
	
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public long getSecondary() {
		return secondary;
	}
	public void setSecondary(long secondary) {
		this.secondary = secondary;
	}
	public String getStateAbbr() {
		return stateAbbr;
	}
	public void setStateAbbr(String stateAbbr) {
		this.stateAbbr = stateAbbr;
	}
	public boolean isManager() {
		return manager;
	}
	public void setManager(boolean manager) {
		this.manager = manager;
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
		if (empID != other.empID)
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (manager != other.manager)
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (phone != other.phone)
			return false;
		if (secondary != other.secondary)
			return false;
		if (stateAbbr == null) {
			if (other.stateAbbr != null)
				return false;
		} else if (!stateAbbr.equals(other.stateAbbr))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + empID;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + (manager ? 1231 : 1237);
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + (int) (phone ^ (phone >>> 32));
		result = prime * result + (int) (secondary ^ (secondary >>> 32));
		result = prime * result + ((stateAbbr == null) ? 0 : stateAbbr.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", username=" + username + ", pass=" + pass + ", email=" + email
				+ ", fName=" + fName + ", lName=" + lName + ", phone=" + phone + ", secondary=" + secondary
				+ ", stateAbbr=" + stateAbbr + ", manager=" + manager + "]";
	}
}

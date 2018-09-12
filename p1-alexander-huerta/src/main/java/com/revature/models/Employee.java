package com.revature.models;

public class Employee {

	private int empId;
	private String fName;
	private String lName;
	private int managerId;
	private String pswrd;
	private String userName;
	
	public Employee() {
		super();
	}
	public Employee(int empId, String fName, String lName, int managerId, String pswrd, String userName) {
		super();
		this.empId = empId;
		this.fName = fName;
		this.lName = lName;
		this.managerId = managerId;
		this.pswrd = pswrd;
		this.userName = userName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getmanagerId() {
		return managerId;
	}

	public void setmanagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getPswrd() {
		return pswrd;
	}

	public void setPswrd(String pswrd) {
		this.pswrd = pswrd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empId;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + managerId;
		result = prime * result + ((pswrd == null) ? 0 : pswrd.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		if (empId != other.empId) {
			return false;
		}
		if (fName == null) {
			if (other.fName != null) {
				return false;
			}
		} else if (!fName.equals(other.fName)) {
			return false;
		}
		if (lName == null) {
			if (other.lName != null) {
				return false;
			}
		} else if (!lName.equals(other.lName)) {
			return false;
		}
		if (managerId != other.managerId) {
			return false;
		}
		if (pswrd == null) {
			if (other.pswrd != null) {
				return false;
			}
		} else if (!pswrd.equals(other.pswrd)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", fName=" + fName + ", lName=" + lName + ", managerId=" + managerId + ", pswrd="
				+ pswrd + ", userName=" + userName + "]";
	}
	
	
}
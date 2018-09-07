package com.revature.model;

public class Employee {

	private int empId;
	private String fName;
	private String lName;
	private int manId;
	private String pswrd;
	
	public Employee() {
		super();
	}
	
	public Employee(int empId, String fName, String lName, int manId, String pswrd) {
		super();
		this.empId = empId;
		this.fName = fName;
		this.lName = lName;
		this.manId = manId;
		this.pswrd = pswrd;
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

	public int getManId() {
		return manId;
	}

	public void setManId(int manId) {
		this.manId = manId;
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
		result = prime * result + manId;
		result = prime * result + ((pswrd == null) ? 0 : pswrd.hashCode());
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
		if (empId != other.empId)
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
		if (manId != other.manId)
			return false;
		if (pswrd == null) {
			if (other.pswrd != null)
				return false;
		} else if (!pswrd.equals(other.pswrd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", fName=" + fName + ", lName=" + lName + ", manId=" + manId + ", pswrd="
				+ pswrd + "]";
	}
	
	
}

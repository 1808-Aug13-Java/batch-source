package com.revature.model;

public class Manager {

	private int manId;
	private String fName;
	private String lName;

	public Manager() {
		super();
	}

	public Manager(int manId, String fName, String lName) {
		super();
		this.manId = manId;
		this.fName = fName;
		this.lName = lName;
	}

	public int getManId() {
		return manId;
	}

	public void setManId(int manId) {
		this.manId = manId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + manId;
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
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName)) {
			return false;
		}
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName)) {
			return false;
		}
		if (manId != other.manId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Manager [manId=" + manId + ", fName=" + fName + ", lName=" + lName + "]";
	}

}

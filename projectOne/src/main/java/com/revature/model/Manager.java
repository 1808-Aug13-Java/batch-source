package com.revature.model;

public class Manager {

	private int manId;
	private String Name;
	private String pswd;

	public Manager() {
		super();
	}

	public Manager(int manId, String Name, String lName) {
		super();
		this.manId = manId;
		this.Name = Name;
		this.pswd = lName;
	}

	public int getManId() {
		return manId;
	}

	public void setManId(int manId) {
		this.manId = manId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + manId;
		result = prime * result + ((pswd == null) ? 0 : pswd.hashCode());
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
		if (!(obj instanceof Manager)) {
			return false;
		}
		Manager other = (Manager) obj;
		if (Name == null) {
			if (other.Name != null) {
				return false;
			}
		} else if (!Name.equals(other.Name)) {
			return false;
		}
		if (manId != other.manId) {
			return false;
		}
		if (pswd == null) {
			if (other.pswd != null) {
				return false;
			}
		} else if (!pswd.equals(other.pswd)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Manager [manId=" + manId + ", Name=" + Name + ", pswd=" + pswd + "]";
	}

}

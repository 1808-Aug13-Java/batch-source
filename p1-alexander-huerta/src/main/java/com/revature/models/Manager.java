package com.revature.models;

public class Manager {

	private int managerId;
	private String name;
	private String pswd;
	private String userName;

	public Manager() {
		super();
	}

	public Manager(int managerId, String name, String pswd, String userName) {
		super();
		this.managerId = managerId;
		this.name = name;
		this.pswd = pswd;
		this.userName = userName;
	}

	public int getmanagerId() {
		return managerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setmanagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		result = prime * result + managerId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pswd == null) ? 0 : pswd.hashCode());
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
		if (!(obj instanceof Manager)) {
			return false;
		}
		Manager other = (Manager) obj;
		if (managerId != other.managerId) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (pswd == null) {
			if (other.pswd != null) {
				return false;
			}
		} else if (!pswd.equals(other.pswd)) {
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
		return "Manager [managerId=" + managerId + ", name=" + name + ", pswd=" + pswd + ", userName=" + userName + "]";
	}

	

}
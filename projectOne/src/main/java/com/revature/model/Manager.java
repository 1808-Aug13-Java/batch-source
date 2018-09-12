package com.revature.model;

public class Manager {

	private int manId;
	private String name;
	private String pswd;
	private String userName;

	public Manager() {
		super();
	}

	public Manager(int manId, String name, String pswd, String userName) {
		super();
		this.manId = manId;
		this.name = name;
		this.pswd = pswd;
		this.userName = userName;
	}
	
	public Manager(int manId, String name, String pswd) {
		super();
		this.manId = manId;
		this.name = name;
		this.pswd = pswd;
	}

	public int getManId() {
		return manId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setManId(int manId) {
		this.manId = manId;
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
		result = prime * result + manId;
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
		if (manId != other.manId) {
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
		return "Manager [manId=" + manId + ", name=" + name + ", pswd=" + pswd + ", userName=" + userName + "]";
	}

	

}

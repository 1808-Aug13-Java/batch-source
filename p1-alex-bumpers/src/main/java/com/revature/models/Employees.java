package com.revature.models;

public class Employees {
	private int id;
	private String name;
	private int managerId;
	private String position;
	private String username;
	private String password;


	private int isManager; // possibly change to boolean???

	public Employees() {
		super();
	}

	public Employees(int id, String name, int managerId, String position, String username, String password,
			int isManager) {
		super();
		this.id = id;
		this.name = name;
		this.managerId = managerId;
		this.position = position;
		this.username = username;
		this.password = password;
		this.isManager = isManager;
	}

	@Override
	public String toString() {
		return "Employees [id=" + id + ", name=" + name + ", managerId=" + managerId + ", position=" + position
				+ ", username=" + username + ", password=" + password + ", isManager=" + isManager + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + isManager;
		result = prime * result + managerId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
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
		Employees other = (Employees) obj;
		if (id != other.id)
			return false;
		if (isManager != other.isManager)
			return false;
		if (managerId != other.managerId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
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

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsManager() {
		return isManager;
	}

	public void setIsManager(int isManager) {
		this.isManager = isManager;
	}
	
	
}
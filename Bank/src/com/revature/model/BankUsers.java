package com.revature.model;

public class BankUsers {
	
	private String userPassword;
	private int id;
	private String username;
	private String email;
	
	public BankUsers() {
		super();
	}
	public BankUsers(String userPassword, int id, String username, String email) {
		super();
		this.userPassword = userPassword;
		this.id = id;
		this.username = username;
		this.email = email;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
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
		BankUsers other = (BankUsers) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id != other.id)
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword)) {
			return false;
		}
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "BankUsers [userPassword=" + userPassword + ", id=" + id + ", username=" + username + ", email=" + email
				+ "]";
	}
	
	
}

	
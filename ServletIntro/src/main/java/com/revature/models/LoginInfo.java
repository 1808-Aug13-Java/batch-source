package com.revature.models;

public class LoginInfo {
	
	private String username;
	private String password;
	
	public LoginInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginInfo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

	@Override
	public String toString() {
		return "LoginInfo [username=" + username + ", password=" + password + "]";
	}
	
	
}

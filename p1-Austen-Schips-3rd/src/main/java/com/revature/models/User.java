package com.revature.models;

public class User {
	
	private int user_id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int role_id;
	private Reimbursement[] pending;
	private Reimbursement[] resolved;
	
	public User(int user_id, String username, String password, String firstName, String lastName, String email,
			int role_id) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role_id = role_id;
	}
	
	public User(int user_id) {
		super();
		this.user_id = user_id;
	}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public Reimbursement[] getPending() {
		return pending;
	}
	public void setPending(Reimbursement[] pending) {
		this.pending = pending;
	}
	public Reimbursement[] getResolved() {
		return resolved;
	}
	public void setResolved(Reimbursement[] resolved) {
		this.resolved = resolved;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", role_id=" + role_id + "]";
	}
	
}
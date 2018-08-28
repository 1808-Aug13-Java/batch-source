package com.revature.model;

public class BankUser {
	private String username;
	private String password;
	private float balance;
	public BankUser() {
		super();
	}
	public BankUser(String username, String password, float balance) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "BankUser [username=" + username + ", password=" + password + ", balance=" + balance + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		BankUser other = (BankUser) obj;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password)) {return false;}
		if (username == null) {
			if (other.username != null) {return false;}
		} else if (!username.equals(other.username)) {return false;}
		return true;
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
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	
}

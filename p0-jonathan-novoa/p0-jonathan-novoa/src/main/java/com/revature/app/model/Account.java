package com.revature.app.model;



public class Account {
	//data members
	private String username;
	private String password;
	private float balance;
	private boolean loggedIn=false;
	
	//public constructor
	public Account(String username, String password, float balance, boolean loggedIn) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.loggedIn = loggedIn;
		//maybe remove boolean from constructor input
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance=balance;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", balance=" + balance + ", loggedIn="
				+ loggedIn + "]";
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
		Account other = (Account) obj;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
		{
			return false;
		}
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
		{
			return false;
		}
		return true;
	}
	public Account() {
		super();

	}

}

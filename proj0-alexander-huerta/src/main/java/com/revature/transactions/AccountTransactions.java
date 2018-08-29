package com.revature.transactions;

public class AccountTransactions {
	
	private String username;			//establishing data entities
	private String password;
	private float currentBalance;
	private boolean activeAcc = false;
	
	public AccountTransactions(String username, String password, float balance, boolean activeAcc) {
		super();
		this.username = username;
		this.password = password;
		this.currentBalance = balance;
		this.activeAcc = activeAcc;
		
	}
	
	public AccountTransactions() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime + result + Float.floatToIntBits(currentBalance);
		result = prime + result + ((username == null) ? 0 : username.hashCode());
		result = prime + result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		AccountTransactions other = (AccountTransactions) obj;
		if (password == null) {
			if(other.password != null)
				return false;
		}else if (!password.equals(other.password))
			return false;
		if (Float.floatToIntBits(currentBalance) != Float.floatToIntBits(other.currentBalance))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		}else if (!username.equals(other.username))
			return false;
		return true;
		
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public boolean isactiveAcc() {
		return activeAcc;
	}
	public void setactiveAcc(boolean activeAcc) {
		this.activeAcc = activeAcc;
	}
	public float getCurrentBalance() {
		return currentBalance;
	}
	public void setBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	@Override
	public String toString() {
		return "Your neew account [username = " + username + ", password = "
				+ password + ", balance = " + currentBalance + "]";
	}
}


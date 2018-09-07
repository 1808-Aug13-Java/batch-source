package com.revature.model;


public class Account {
	
	private String userName;
	private double ballance;

	public Account() {
		super();
		ballance = 0;
		userName = null;
	}

	public Account(String userName, double ballance) {
		super();
		this.userName = userName;
		this.ballance = ballance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUser(String userName) {
		this.userName = userName;
	}

	public double getBallance() {
		return ballance;
	}

	public void setBallance(double ballance) {
		this.ballance = ballance;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ballance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		if (Double.doubleToLongBits(ballance) != Double.doubleToLongBits(other.ballance))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Account [userName=" + userName + ", ballance=" + ballance + "]";
	}

	
	
}

package com.revature.model;

import java.math.BigDecimal;

public class Balance {
	private String username;
	private BigDecimal money;
	public Balance() {
		super();
	}
	public Balance(String username, BigDecimal money) {
		super();
		this.username = username;
		this.money = money;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((money == null) ? 0 : money.hashCode());
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
		Balance other = (Balance) obj;
		if (money == null) {
			if (other.money != null)
				return false;
		} else if (!money.equals(other.money)) {
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
		return "Balance [username=" + username + ", money=" + money + "]";
	}
	
}

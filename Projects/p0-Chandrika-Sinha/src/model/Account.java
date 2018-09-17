package model;

import java.math.BigDecimal;

import dao.AccountDaoImpl;

public class Account {
	private int id;
	private BigDecimal balance;
	private String type;
	private boolean isJoint;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int id, BigDecimal balance, String type, boolean isJoint) {
		super();
		this.id = id;
		this.balance = balance;
		this.type = type;
		this.isJoint = isJoint;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isJoint() {
		return isJoint;
	}
	public void setJoint(boolean isJoint) {
		this.isJoint = isJoint;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + id;
		result = prime * result + (isJoint ? 1231 : 1237);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (id != other.id)
			return false;
		if (isJoint != other.isJoint)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", type=" + type + ", isJoint=" + isJoint + "]";
	}	
}

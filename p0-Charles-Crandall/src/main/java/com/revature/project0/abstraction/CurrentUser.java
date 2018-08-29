package com.revature.project0.abstraction;

import java.util.List;

public class CurrentUser {

	private int customerID;
	private String username;
	private String address;
	private List<Integer> accounts;
	public CurrentUser() {
		super();
	}
	public CurrentUser(int custID, String username, String address, List<Integer> accounts) {
		super();
		this.customerID = custID;
		this.username = username;
		this.address = address;
		this.accounts = accounts;
	}
	public int getCustID() {
		return customerID;
	}
	public void setCustID(int custID) {
		this.customerID = custID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Integer> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Integer> accounts) {
		this.accounts = accounts;
	}
	public void addAccount(Integer account) {
		accounts.add(account);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + customerID;
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
		CurrentUser other = (CurrentUser) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (customerID != other.customerID)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CurrentUser [CUST_ID=" + customerID + ", username=" + username + ", address=" + address + ", accounts="
				+ accounts + "]";
	}
	
	
}

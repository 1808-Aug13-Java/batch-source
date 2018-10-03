package com.revature.models;

import java.sql.Date;

public class Invoice {
	private int id;
	private Date invDate;
	private int amount;
	private int cust_id;
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Invoice(int id, Date invDate, int amount, int cust_id) {
		super();
		this.id = id;
		this.invDate = invDate;
		this.amount = amount;
		this.cust_id = cust_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getInvDate() {
		return invDate;
	}
	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + cust_id;
		result = prime * result + id;
		result = prime * result + ((invDate == null) ? 0 : invDate.hashCode());
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
		Invoice other = (Invoice) obj;
		if (amount != other.amount)
			return false;
		if (cust_id != other.cust_id)
			return false;
		if (id != other.id)
			return false;
		if (invDate == null) {
			if (other.invDate != null)
				return false;
		} else if (!invDate.equals(other.invDate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invDate=" + invDate + ", amount=" + amount + ", cust_id=" + cust_id + "]";
	}
	
}
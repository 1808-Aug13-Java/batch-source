package com.revature.models;

import java.sql.Date;

public class Invoice {
	private int id;
	private Date inv_date;
	private float amount;
	private Customer customer;
	/**
	 * 
	 */
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param inv_date
	 * @param amount
	 * @param customer
	 */
	public Invoice(int id, Date inv_date, float amount, Customer customer) {
		super();
		this.id = id;
		this.inv_date = inv_date;
		this.amount = amount;
		this.customer = customer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + id;
		result = prime * result + ((inv_date == null) ? 0 : inv_date.hashCode());
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
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (id != other.id)
			return false;
		if (inv_date == null) {
			if (other.inv_date != null)
				return false;
		} else if (!inv_date.equals(other.inv_date))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", inv_date=" + inv_date + ", amount=" + amount + ", customer=" + customer + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getInv_date() {
		return inv_date;
	}
	public void setInv_date(Date inv_date) {
		this.inv_date = inv_date;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
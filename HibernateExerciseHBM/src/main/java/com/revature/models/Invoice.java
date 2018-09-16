package com.revature.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
	
	private int id;
	private Date date;
	private BigDecimal amt;
	private Customer customer;
	private List<Invoice> invoices = new ArrayList<>();
	/**
	 * 
	 */
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param date
	 * @param amt
	 * @param customer
	 * @param invoices
	 */
	public Invoice(int id, Date date, BigDecimal amt, Customer customer, List<Invoice> invoices) {
		super();
		this.id = id;
		this.date = date;
		this.amt = amt;
		this.customer = customer;
		this.invoices = invoices;
	}
	
	/**
	 * @param date
	 * @param amt
	 * @param customer
	 */
	public Invoice(Date date, BigDecimal amt, Customer customer) {
		super();
		this.date = date;
		this.amt = amt;
		this.customer = customer;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the amt
	 */
	public BigDecimal getAmt() {
		return amt;
	}
	/**
	 * @param amt the amt to set
	 */
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @return the invoices
	 */
	public List<Invoice> getInvoices() {
		return invoices;
	}
	/**
	 * @param invoices the invoices to set
	 */
	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amt == null) ? 0 : amt.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((invoices == null) ? 0 : invoices.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (amt == null) {
			if (other.amt != null)
				return false;
		} else if (!amt.equals(other.amt))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (invoices == null) {
			if (other.invoices != null)
				return false;
		} else if (!invoices.equals(other.invoices))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", date=" + date + ", amt=" + amt + ", customer=" + customer + ", invoices="
				+ invoices + "]";
	}

}

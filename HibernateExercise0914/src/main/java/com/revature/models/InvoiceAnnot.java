package com.revature.models;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table

public class InvoiceAnnot {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoiceSequence")
	@SequenceGenerator(name = "invoiceSequence", allocationSize = 1, sequenceName = "SQ_INVOICE_PK")
	@Column(name = "INV_ID")
	int id;

	@Column
	String inv_date;

	@Column
	double amount;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "CUST_ID")
	private CustomerAnnot customer;

	// @Column
	// CustomerAnnot customer;

	public InvoiceAnnot() {
		super();
	}

	public InvoiceAnnot(int id, String inv_date, double amount, CustomerAnnot customer) {
		super();
		this.id = id;
		this.inv_date = inv_date;
		this.amount = amount;
		this.customer = customer;
	}
	
	public InvoiceAnnot(String inv_date, double amount, CustomerAnnot customer) {
		super();
		this.inv_date = inv_date;
		this.amount = amount;
		this.customer = customer;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInv_date() {
		return inv_date;
	}

	public void setInv_date(String inv_date) {
		this.inv_date = inv_date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public CustomerAnnot getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerAnnot customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		InvoiceAnnot other = (InvoiceAnnot) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
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
		return "InvoiceAnnot [id=" + id + ", inv_date=" + inv_date + ", amount=" + amount + ", customer=" + customer
				+ "]";
	}

}

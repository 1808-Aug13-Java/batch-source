package com.revature.models;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//invoice id, a date, and amount
@Entity
@Table
public class Invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invoiceSequence")
	@SequenceGenerator(name="invoiceSequence",allocationSize=1,sequenceName="SQ_INVOICE_PK")
	@Column(name="INVOICE_ID")
	private int id;
	@Column(name="Creation Date")
	private Date date;
	private BigDecimal amount;
	@ManyToOne
	@JoinColumn(name="CUST_ID")
	private Customer customer;
	
	public int getId() {
		return id;
	}
	public Invoice(int id, Date date, BigDecimal amount, Customer customer) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.customer = customer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
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
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
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
		return true;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", date=" + date + ", amount=" + amount + ", customer=" + customer + "]";
	}
	public Invoice(int id, Date date, BigDecimal amount) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
	}
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
}

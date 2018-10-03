package com.revature.models;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name="HBM_INVOICE")
public class Invoice {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invSequence")
	@SequenceGenerator(name="invSequence", allocationSize=1, sequenceName="SQ_INV_PK")
	@Column(name="INV_ID")
	private int id;
	
	@Column(name="INV_DATE")
	private Date invDate;
	
	@Column(name="INV_AMOUNT")
	private int amount;
	
	@ManyToOne
	@JoinColumn(name="CUST_ID")
	private Customer customer;
	
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Invoice(int id, Date invDate, int amount, Customer customer) {
		super();
		this.id = id;
		this.invDate = invDate;
		this.amount = amount;
		this.customer = customer;
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
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
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
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
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
		return "Invoice [id=" + id + ", invDate=" + invDate + ", amount=" + amount + ", customer=" + customer + "]";
	}
	
	
}
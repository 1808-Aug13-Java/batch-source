package com.revature.models;

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

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Invoices {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invoiceIdGenerator")
	@SequenceGenerator(
			name="invoiceIdGenerator", allocationSize=1, sequenceName="SQ_INVOICES_PK")
	@Column(name="INVOICE_ID")
	private int id;
	
	@Column
	private Date date;
	
	@Column
	private float amount;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customers customer;

	public Invoices() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoices(int id, Date date, float amount, Customers customer) {
		super();
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.customer = customer;
	}

	public int getId() {
		return id;
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
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
		Invoices other = (Invoices) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
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
		return "Invoices [id=" + id + ", date=" + date + ", amount=" + amount + ", customer=" + customer + "]";
	}
	
	
}

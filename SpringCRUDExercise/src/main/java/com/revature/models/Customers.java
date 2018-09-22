package com.revature.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Customers {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idGenerator")
	@SequenceGenerator(
			name="idGenerator", allocationSize=1, sequenceName="SQ_CUSTOMERS_PK")
	@Column(name="CUSTOMER_ID")
	private int id;
	
	@Column
	private int phoneNumber;
	
	@OneToMany(mappedBy="customers")
	private List<Invoices> invoices = new ArrayList<>();

	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customers(int id, int phoneNumber, List<Invoices> invoices) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.invoices = invoices;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Invoices> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoices> invoices) {
		this.invoices = invoices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((invoices == null) ? 0 : invoices.hashCode());
		result = prime * result + phoneNumber;
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
		Customers other = (Customers) obj;
		if (id != other.id)
			return false;
		if (invoices == null) {
			if (other.invoices != null)
				return false;
		} else if (!invoices.equals(other.invoices))
			return false;
		if (phoneNumber != other.phoneNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customers [id=" + id + ", phoneNumber=" + phoneNumber + ", invoices=" + invoices + "]";
	}
	
	
	
}

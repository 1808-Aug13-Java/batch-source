package com.revature.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customerSequence")
	@SequenceGenerator(name="customerSequence", allocationSize=1, sequenceName="SQ_CUSTOMER_PK")
	@Column(name="CUSTOMER_ID")
	private int id;
	
	@Column
	private String name;
	
	@Column
	private int phoneNumber;
	
	@OneToMany(mappedBy="customer")
	private List<Invoice> invoices = new ArrayList<>();
	
	public Customer() {
		super();
	}
	
	public Customer(int id, String name, int phoneNumber, List<Invoice> invoices) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.invoices = invoices;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public List<Invoice> getInvoices() {
		return invoices;
	}
	
	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((invoices == null) ? 0 : invoices.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		if (invoices == null) {
			if (other.invoices != null)
				return false;
		} else if (!invoices.equals(other.invoices))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber != other.phoneNumber)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", invoices=" + invoices
				+ "]";
	}
	
	
	

}

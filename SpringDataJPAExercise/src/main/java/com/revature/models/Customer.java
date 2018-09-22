package com.revature.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER_JPA")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customerSequence")
	@SequenceGenerator(name="customerSequence", allocationSize=1, sequenceName="SQ_CUST_SEQUENCE")
	@Column(name="CUST_ID")
	private int id;

	@Column(name="CUST_NAME")
	private String name;
	@Column
	private long phone;
	@OneToMany
	@JoinColumn(name="INV_ID")
	private List<Invoice> inv;
	
	public Customer() {
		super();
	}
	
	public Customer(int id, String name, long phone, ArrayList<Invoice> inv) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.inv = inv;
	}
	
	public Customer(int id, String name, long phone) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
	}
	
	public Customer(String name, long phone) {
		super();
		this.id = 0;
		this.name = name;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public List<Invoice> getInv() {
		return inv;
	}
	
	public void setInv(List<Invoice> inv) {
		this.inv = inv;
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

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public void addInvoice(Invoice inv) {
		this.inv.add(inv);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((inv == null) ? 0 : inv.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (phone ^ (phone >>> 32));
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
		if (inv == null) {
			if (other.inv != null)
				return false;
		} else if (!inv.equals(other.inv))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone != other.phone)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerExersize [id=" + id + ", name=" + name + ", phone=" + phone + ", inv=" + inv + "]";
	}
}

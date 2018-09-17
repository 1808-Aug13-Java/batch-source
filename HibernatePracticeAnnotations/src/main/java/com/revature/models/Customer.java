package com.revature.models;

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
@Table
public class Customer {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="custSequence")
	@SequenceGenerator(name="custSequence", allocationSize=1, sequenceName="SQ_CUST_PK")
	@Column(name="CUST_ID")
	private int id;
	@Column(name="NAME")
	private String name;
	@Column(name="PHONE")
	private String phone;
	
	@OneToMany
	@JoinColumn(name="CAVE_ID")
	private List<Invoice> invoices;
	
	public Customer() {
		super();
	}

	public Customer(int id, String name, String phone, List<Invoice> invoices) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.invoices = invoices;
	}
	
	public Customer(int id, String name, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", invoices=" + invoices + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((invoices == null) ? 0 : invoices.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer other = (Customer) obj;
		if (id != other.id) {
			return false;
		}
		if (invoices == null) {
			if (other.invoices != null) {
				return false;
			}
		} else if (!invoices.equals(other.invoices)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (phone == null) {
			if (other.phone != null) {
				return false;
			}
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		return true;
	}

}

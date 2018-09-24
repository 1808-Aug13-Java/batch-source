package com.revature.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;



@Entity
@Table
public class Customer 
{
	@Column(name="CUST_NAME")
	private String name;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="custSequence")
	@SequenceGenerator(name="custSequence", allocationSize=1, sequenceName="SQ_CUST_PK")
	@Column(name="CUST_ID")
	private int id;
	
	@Column(name="CUST_PHONE")
	private int phone;
	
	public Customer(String name, int id, int phone) {
		super();
		this.name = name;
		this.id = id;
		this.phone = phone;
	}
	
	public Customer(String name, int phone) {
		super();
		this.name = name;
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", id=" + id + ", phone=" + phone + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + phone;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (id != other.id)
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}

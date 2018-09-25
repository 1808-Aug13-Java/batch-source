package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table (name="CUSTOMERS")
@Component
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customerSequence")
	@SequenceGenerator(name="customerSequence", allocationSize=1, sequenceName="SQ_CUSTOMER_PK")
	@Column(name="CUSTOMER_ID")
	private int id;
	@Column
	private String name;
	@Column
	private int phonenumber;
	
	public Customer() {
		super();
	}
	public Customer(int id, String name, int phonenumber) {
		super();
		this.id = id;
		this.name = name;
		this.phonenumber = phonenumber;
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
		return phonenumber;
	}
	public void setPhoneNumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + phonenumber;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phonenumber != other.phonenumber)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phonenumber=" + phonenumber + "]";
	}
}

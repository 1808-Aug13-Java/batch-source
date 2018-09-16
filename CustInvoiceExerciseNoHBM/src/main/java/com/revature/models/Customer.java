package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//id, a name, and a phone number
@Entity
@Table
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="custSequence")
	@SequenceGenerator(name="custSequence",allocationSize=1,sequenceName="SQ_CUST_PK")
	@Column(name="CUST_ID")
	private int id;
	private String name;
	@Column(name="PHONE_NUMBER")
	private String number;
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
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", number=" + number + "]";
	}
	public Customer(int id, String name, String number) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

package com.revature.models;

import javax.persistence.*;

@Entity
@Table
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customerSequence")
	@SequenceGenerator(name="customerSequence", allocationSize=1, sequenceName="SQ_CUSTOMER_PK")
	@Column(name="CUST_ID")
	private int id;
	
	@Column(name="CUST_NAME")
	String name;
	
	@Column(name="CUST_PHONE")
	String phoneNumber;
	
	
	public Customer(int id, String name, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}


	public Customer() {
		super();
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


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id 
				+ ", name=" + name 
				+ ", phoneNumber=" + phoneNumber 
				+ "]";
	}
	
	
	
}

package com.revature.models;

public class Customer {
	private int id;
	String name;
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

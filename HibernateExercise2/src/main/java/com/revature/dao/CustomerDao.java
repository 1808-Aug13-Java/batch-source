package com.revature.dao;

import java.util.List;

import com.revature.models.Customer;


public interface CustomerDao {
	
	public List<Customer> getCustomers();
	public Customer getCustomerById(int id);
	public int createCustomer(Customer c);
	public List<Customer> getCustomersByName(String name);
	public long getCustomerCount();

}

package com.revature.dao;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDao {
	
	public Customer getCustomerById(int id);
	public List<Customer> getCustomers();
	
	public int createCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public void deleteCustomerById(int id);
	
}

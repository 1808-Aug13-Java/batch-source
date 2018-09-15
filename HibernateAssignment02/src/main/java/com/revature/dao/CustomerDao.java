package com.revature.dao;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDao {
	public Customer getCustomerById(int custId);
	public List<Customer> getCustomers();
	
	public int createCustomer(Customer cust);
	public void updateCustomer(Customer cust);
	public void deleteCustomerById(int custId);
	
}

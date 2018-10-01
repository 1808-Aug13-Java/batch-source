package com.revature.services;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerService {

	
	public Customer createCustomer(Customer customer);
	public Customer getCustomerById(int id);
	public List<Customer> getCustomers();
	public Customer updateCustomer(Customer customer);
	public Customer deleteCustomer(Customer customer);
	
}

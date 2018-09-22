package com.revature.services;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerServiceInterface {

	public List<Customer> findAllCustomers();
	public Customer findCustomerById(Integer id);
	public Customer addCustomer(Customer c);
	public Customer updateCustomer(Customer c);
	public Customer deleteCustomer(Customer c);
	public Customer deleteCustomerById(Integer id);
	
}

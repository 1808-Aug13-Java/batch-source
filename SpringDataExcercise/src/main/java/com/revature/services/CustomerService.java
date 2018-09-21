package com.revature.services;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerService {
	public List<Customer> findAllCustomers();
	public Customer findCustomerById(Long id);
	public Customer addCustomer(Customer newCustomer);
	public Customer updateCustomer(Customer customer);
	public Customer deleteCustomer(Customer customer);
}

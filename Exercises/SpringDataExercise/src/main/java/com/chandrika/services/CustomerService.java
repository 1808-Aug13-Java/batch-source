package com.chandrika.services;

import java.util.List;

import com.chandrika.models.Customer;

public interface CustomerService {
	public List<Customer> getAllCustomers();
	public Customer getCustomerById(long id);
	public Customer addCustomer(Customer newCustomer);
	public Customer updateCustomer(Customer customer);
	public Customer deleteCustomer(Customer customer);
}

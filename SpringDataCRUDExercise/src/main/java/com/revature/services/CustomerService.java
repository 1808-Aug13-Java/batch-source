package com.revature.services;

import java.util.List;

import com.revature.models.Customers;

public interface CustomerService {
	
	public List<Customers> findAllCustomers();
	public Customers findCustomerById(Long id);
	public Customers addCustomer(Customers newCustomer);
	public Customers updateCustomer(Customers customer);
	public Customers deleteCustomer(Customers customer);

}

package com.revature.services;

import java.util.List;

import com.revature.models.Customers;

public interface CustomerService {
	public Customers createCustomer(Customers customer);
	public Customers getCustomerById(int id);
	public List<Customers> getCustomers();
	public Customers updateCustomer(Customers customer);
	public Customers deleteCustomer(Customers customer);
}

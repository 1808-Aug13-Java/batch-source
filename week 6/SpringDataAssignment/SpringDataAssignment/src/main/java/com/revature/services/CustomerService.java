package com.revature.services;

import java.util.List;

import com.revature.models.Customers;

public interface CustomerService {
	
	public List<Customers> findAllCustomers();
	public Customers findCustomersById(Long id);
	public Customers addCustomers(Customers newUser);
	public Customers updateCustomers(Customers customer);
	public void deleteCustomers(Long id);

}

package com.revature.dao;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDAO {

	public List<Customer> getCusomers();
	public Customer getCustomerById(int id);
	public int createCustomer(Customer cust);
	public int deleteCustomer(Customer cust);
	
}

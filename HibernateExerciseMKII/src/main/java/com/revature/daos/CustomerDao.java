package com.revature.daos;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDao {	
	public int createCustomer(Customer C);
	public int deleteCustomerById(int customerId);
	public int updateCustomerById(int customerId);
	public Customer getCustomerById(int customerId);
	public List<Customer> getAllCustomers();
	
}

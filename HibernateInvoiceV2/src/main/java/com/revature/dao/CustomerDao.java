package com.revature.dao;

import java.util.List;

import com.revature.models.Customer;



public interface CustomerDao 
{
	public List<Customer> getCustomer();
	public Customer getCustomerById(int id);
	public int createCustomer(Customer b);
	public void updateCustomer(Customer c);
	public void deleteCustomer(int id);

}

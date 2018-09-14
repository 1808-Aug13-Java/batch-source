package com.revature.daos;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDao {
	public int createCustomer(Customer customer);
	public Customer getCustomerById(int id);
	public List<Customer> getCustomers();
	public void updateCustomer(Customer customer);
	public void deleteCustomer(Customer customer);
}

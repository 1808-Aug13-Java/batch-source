package com.chandrika.daos;

import java.util.List;

import com.chandrika.models.Customer;

public interface CustomerDao {
	public List<Customer> getCustomers();
	public Customer getCustomerById(int id);
	public void updateCustomer(Customer c);
	public void deleteCustomerById(int id);
	public int createCustomer(Customer customer);
}
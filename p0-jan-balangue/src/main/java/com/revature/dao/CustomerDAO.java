package com.revature.dao;

import java.util.List;

import com.revature.bank.Customer;

public interface CustomerDAO {
	public List<Customer> getCustomers();
	public Customer getCustomerById(int id);
	public int createCustomer(Customer customer);
	public int updateCustomer(Customer customer);
	public int deleteCustomerById(int id);
}

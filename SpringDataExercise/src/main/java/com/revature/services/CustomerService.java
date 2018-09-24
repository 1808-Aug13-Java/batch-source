package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.Customer;

@Service
public interface CustomerService {
	public List<Customer> getAllCustomers();
	public Customer getCustomerById(int id);
	public Customer addCustomer(Customer cust);
	public void updateCustomer(Customer cust);
	public void deleteCustomerById(int id);
	
}

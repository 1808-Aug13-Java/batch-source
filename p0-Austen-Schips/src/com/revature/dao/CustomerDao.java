package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Customers;

public interface CustomerDao {
	public List<Customers> getCustomers();
	public Customers getCustomerByUsername(String userName);
	public int createCustomer(Customers customer);
	public int increaseBalance(String user, float increaseAmount);
	
}

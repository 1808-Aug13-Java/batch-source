package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Customers;

public interface CustomerDao {

	public Customers getCustomersByUsername(String userName);
	public int createCustomer(Customers customer);
	public int createPassword(Customers userPassword);
}

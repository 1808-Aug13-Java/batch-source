package com.revature.daos;

import java.util.List;

import com.revature.models.Customers;

public interface CustomersDao {


	public List<Customers> getCustomers();
	public Customers getCustomersById(int id);
	public int createCustomers(Customers c);
	public List<Customers> getCustomersByPhone(long phone);
	public long getCustomersCount();
	public void deleteCustomersById(int id);
	public void updateCustomers(Customers c);
	List<Customers> getCustomersByName(String name);
	
}

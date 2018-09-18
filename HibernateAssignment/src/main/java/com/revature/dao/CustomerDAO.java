package com.revature.dao;
import java.util.List;
import com.revature.models.Customer;
public interface CustomerDAO {
	public List<Customer> getCustomers();
	public Customer getCustomerById(int id);
	public int createCustomer(Customer c);
	public List<Customer> getCustomerByName(String name);
	public long getCustomerCount();
	public void deleteCustomer(int id);
}

package com.revature.daos;

import java.util.List;

import com.revature.models.CustomerExersize;
import com.revature.models.InvoiceExersize;

public interface CustomerDaoInterface {

	public List<CustomerExersize> getAllCustomers();
	public CustomerExersize getCustomerById(int id);
	public int newCustomer(CustomerExersize c);
	public void updateCustomer(CustomerExersize c);
	public void deleteCustomer(int id);
}

package com.revature.services;

import java.util.List;

import com.revature.models.CustomerHibernate;

public interface CustomerService {
	public List<CustomerHibernate> findAllCustomers();
	public CustomerHibernate findCustomerByName(String name);
	public CustomerHibernate saveCustomer(CustomerHibernate newCustomer);
	public CustomerHibernate updateCustomer(CustomerHibernate customer);
	public void deleteCustomer(CustomerHibernate customer);
}

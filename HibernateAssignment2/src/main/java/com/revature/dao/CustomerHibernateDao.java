package com.revature.dao;

import java.util.List;

import com.revature.models.CustomerHibernate;

public interface CustomerHibernateDao {
	public List<CustomerHibernate> getCustomers();
	public CustomerHibernate getCustomerById(int id);
	public int createCustomer(CustomerHibernate c);
	public void updateCustomer(CustomerHibernate c);
	public void deleteCustomerById(int id);
}

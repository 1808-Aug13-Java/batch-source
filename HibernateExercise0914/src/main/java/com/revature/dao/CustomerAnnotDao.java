package com.revature.dao;

import java.util.List;

import com.revature.models.CustomerAnnot;


public interface CustomerAnnotDao {
	
	public List<CustomerAnnot> getCustomer();
	public List<CustomerAnnot> getCustomerById(int id);
	public int createCustomer(CustomerAnnot c);
	public void deleteCustomer(int id);
	public void updateCustomer(CustomerAnnot c);
	

}

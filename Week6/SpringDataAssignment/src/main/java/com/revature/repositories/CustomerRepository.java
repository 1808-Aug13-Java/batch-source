package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	public Customer findCustomerById( int id);
	public Customer findCustomerByPhonenumber(int phonenumber);
	public Customer findCustomerByName(String name);
	
}
 
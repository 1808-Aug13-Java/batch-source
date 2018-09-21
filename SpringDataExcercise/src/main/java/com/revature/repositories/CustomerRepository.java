package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	public Customer findCustomerByName(String name);
	public Customer findCustomerById(String id);
}

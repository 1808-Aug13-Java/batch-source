package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Customer;
import com.revature.repositories.CustomerRepository;

@Service
public class CustomerService implements CustomerServiceInterface{

	@Autowired
	CustomerRepository customerRepo;
	
	@Override
	public List<Customer> findAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Customer findCustomerById(Integer id) {
		return customerRepo.getOne(id);
	}

	@Override
	public Customer addCustomer(Customer c) {
		return customerRepo.save(c);
	}

	@Override
	public Customer updateCustomer(Customer c) {
		return customerRepo.save(c);
	}

	@Override
	public Customer deleteCustomer(Customer c) {
		customerRepo.delete(c);
		return c;
	}

	@Override
	public Customer deleteCustomerById(Integer id) {
		Customer c = findCustomerById(id);
		customerRepo.delete(c);
		return c;
	}

}

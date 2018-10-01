package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Customer;
import com.revature.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	CustomerRepository customerRepo;
	
	@Override
	public Customer createCustomer(Customer customer) {
		customerRepo.save(customer);
		return customer;
	}

	@Override
	public Customer getCustomerById(int id) {
		Customer c = customerRepo.getOne(id);
		return c;
	}

	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = customerRepo.findAll();
		return customers;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		customerRepo.save(customer);
		return customer;
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		customerRepo.delete(customer);
		return customer;
	}

}

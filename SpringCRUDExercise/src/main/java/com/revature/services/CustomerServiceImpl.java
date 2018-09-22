package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.models.Customers;
import com.revature.repositories.CustomerRepository;

public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customers createCustomer(Customers customer) {
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public Customers getCustomerById(int id) {
		Customers c = customerRepository.getOne(id);
		return c;
	}

	@Override
	public List<Customers> getCustomers() {
		List<Customers> customers = customerRepository.findAll();
		return customers;
	}

	@Override
	public Customers updateCustomer(Customers customer) {
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public Customers deleteCustomer(Customers customer) {
		customerRepository.delete(customer);
		return customer;
	}
}

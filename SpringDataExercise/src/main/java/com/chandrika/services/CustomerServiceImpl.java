package com.chandrika.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chandrika.models.Customer;
import com.chandrika.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepo;
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Customer getCustomerById(long id) {
		return customerRepo.getOne(id);
	}

	@Override
	public Customer addCustomer(Customer newCustomer) {
		return customerRepo.save(newCustomer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		customerRepo.delete(customer);
		return customer;
	}
	
}

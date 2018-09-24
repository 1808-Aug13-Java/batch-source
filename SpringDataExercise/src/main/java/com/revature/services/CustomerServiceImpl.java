package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Customer;
import com.revature.repositories.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	/** The database interface for customers. */
	@Autowired
	CustomerRepository custRepo;
	
	
	@Override
	public List<Customer> getAllCustomers() {
		return custRepo.findAll();
	}

	@Override
	public Customer getCustomerById(int id) {
		// Attempt to locate and return the customer by id. Return null if not 
		// found. 
		Optional<Customer> opt = custRepo.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public Customer addCustomer(Customer cust) {
		return custRepo.save(cust);
	}

	@Override
	public void updateCustomer(Customer cust) {
		custRepo.save(cust);
	}

	@Override
	public void deleteCustomerById(int id) {
		custRepo.deleteById(id);
	}

}

package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Customer;
import com.revature.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepo;
	
	@Override
	public List<Customer> findAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Customer findCustomerById(int id) {
		return customerRepo.findCustomerById(id);
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
	public void deleteCustomer(Customer customer) {
		customerRepo.delete(customer);
	}

}

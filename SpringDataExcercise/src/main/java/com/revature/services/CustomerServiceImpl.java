package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Customer;
import com.revature.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerRepository CustomerRepo;
	
	@Override
	public List<Customer> findAllCustomers() {
		return CustomerRepo.findAll();
	}

	@Override
	public Customer findCustomerById(Long id) {
		return CustomerRepo.getOne(id);
	}

	@Override
	public Customer addCustomer(Customer newCustomer) {
		return CustomerRepo.save(newCustomer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return CustomerRepo.save(customer);
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		CustomerRepo.delete(customer);
		return customer;
		
	}


}

package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Customers;
import com.revature.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepo;

	@Override
	public List<Customers> findAllCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public Customers findCustomerById(Long id) {
		return customerRepo.getOne(id);
	}

	@Override
	public Customers addCustomer(Customers newCustomer) {
		return customerRepo.save(newCustomer);
	}

	@Override
	public Customers updateCustomer(Customers customer) {
		return customerRepo.save(customer); // save will do an update if it finds an entry already in dB
		// but if Id is empty will do insert
	}

	@Override
	public Customers deleteCustomer(Customers customer) {
		customerRepo.delete(customer);
		return customer;
	}

}

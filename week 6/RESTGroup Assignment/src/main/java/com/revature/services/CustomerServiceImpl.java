package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Customers;
import com.revature.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customRepo;
	
	@Override
	public List<Customers> findAllCustomers() {
		return customRepo.findAll();
		}

	@Override
	public Customers findCustomersById(Long id) {
		return customRepo.getOne(id);
	}

	@Override
	public Customers addCustomers(Customers newUser) {
		return customRepo.save(newUser);
		
	}

	@Override
	public Customers updateCustomers(Customers customer) {
		return customRepo.save(customer);
	}

	@Override
	public void deleteCustomers(Long id) {
		customRepo.deleteById(id);
	}

}

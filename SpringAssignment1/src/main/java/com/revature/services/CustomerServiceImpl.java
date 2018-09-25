package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.CustomerHibernate;
import com.revature.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository cusRepo;
	
	public List<CustomerHibernate> findAllCustomers(){
		return cusRepo.findAll();
	}
	
	public CustomerHibernate findCustomerByName(String name) {
		return cusRepo.findCustomerByName(name);
	}
	public CustomerHibernate saveCustomer(CustomerHibernate newCustomer) {
		return cusRepo.save(newCustomer);
	}
	public CustomerHibernate updateCustomer(CustomerHibernate customer) {
		return cusRepo.save(customer);
	}
	public void deleteCustomer(CustomerHibernate customer) {
		cusRepo.delete(customer);
	}
}

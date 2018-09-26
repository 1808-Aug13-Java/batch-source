package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private Logger logger = Logger.getLogger(CustomerController.class.getName());
	
	private List<Customer> customers = new ArrayList<>();
	
	public CustomerController() {
		customers.add(new Customer(1, "Billy Pilgrim", "billp@gmail.com", null));
		customers.add(new Customer(2, "Jennifer Lawrence", "jenl@gmail.com", null));
		customers.add(new Customer(3, "Bart Simpson", "bsimps@gmail.com", null));
		customers.add(new Customer(4, "Kurt Vonnegut", "kvon@gmail.com", null));
	}
	
	@GetMapping
	public List<Customer> getAllCustomers(){
		logger.info("CustomerController.findAllCustomers(");
		return customers;
	}
	
	@GetMapping(value="/{id}")
	public Customer getCustomerById(@PathVariable("id") int customerId) {
		logger.info("CustomerController.getCustomerById");
		Customer customer = customers.stream().filter(cust -> cust.getCustomerId() == customerId).findFirst().get();
		return customer;
	}

}

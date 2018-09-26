package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.intercom.AccountClient;
import com.revature.models.Account;
import com.revature.models.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	AccountClient accountClient;
	
	private Logger logger = Logger.getLogger(CustomerController.class.getName());

	private List<Customer> customers = new ArrayList<Customer>();
	
	public CustomerController() {
		customers.add(new Customer(1, "Billy Pilgrim", "billp@gmail.com", null));
		customers.add(new Customer(2, "Jennifer Lawrence", "jlaw@gmail.com", null));
		customers.add(new Customer(3, "Bart Simpson", "bsimpson@gmail.com", null));
		customers.add(new Customer(4, "Kurt Vonnegut", "kvon@gmail.com", null));
	}
	
	@GetMapping
	public List<Customer> getAllCustomers() {
		logger.info("CustomerController.findAllCustomers");
		return customers;
	}
	
	@GetMapping(value="/{id}")
	public Customer getCustomerById(@PathVariable("id") int id) {
		logger.info("CustomerController.findCustomerById");
		Customer customer = customers.stream().filter(cust -> cust.getCustomerId() == id).findFirst().get();
//		make a call to our interservice AccountService get accounts by customer id
		List<Account> accounts = accountClient.getAccountsByCustomerId(id);
		customer.setAccounts(accounts);
		return customer;
	}
	
	
	
	
}

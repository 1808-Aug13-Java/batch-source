package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
	private Logger logger =Logger.getLogger(CustomerController.class.getName());
	
	private List<Customer> customers= new ArrayList<>();
	
	@Autowired
	AccountClient accountClient;
	
	public CustomerController() {
		//in a real application we would be getting this information from a database
		
		customers.add(new Customer(1,"Billy Pilgrim", "bill@gmail.com",null));
		customers.add(new Customer(4,"Some person", "email@gmail.com",null));
	}
	
	@GetMapping
	public List<Customer> getAllCustomer(){
		logger.info("CustomerController.findAllCustomers()");
		return customers;
	}
	
	@GetMapping(value="/{id}")
	///set up calls among services to add more connectivity
	public Customer getCustomerById(@PathVariable("id") int customerId) {
		logger.info("CustomerControler.getCustommerById()");
		Customer customer=customers.stream().filter(cust -> cust.getCustomerId()==
				customerId).findFirst().get();
		
		
		List<Account> accounts= accountClient.getAllaccounts(customerId);
		return customer;
	}
	




}

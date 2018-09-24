package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Customer;
import com.revature.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerServ;
	
	@GetMapping(value="users", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findAllCustomers(){
		return customerServ.findAllCustomers();
	}
	
	@GetMapping(value="users/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer findCustomerById(@PathVariable("id") int customerId) {
		return customerServ.findCustomerById(customerId);
	}
	

}

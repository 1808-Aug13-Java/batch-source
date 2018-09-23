package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Customer;
import com.revature.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findAllCustomers() {
		return customerService.findAllCustomers();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer findCustomerById(@PathVariable("id") Long id) {
		return customerService.findCustomerById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public Customer addCustomer(@RequestBody Customer c) {
		return customerService.addCustomer(c);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public Customer updateCustomer(@RequestBody Customer c) {
		return customerService.updateCustomer(c);
	}
	
	@DeleteMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public Customer deleteCustomer(@RequestBody Customer c) {
		return customerService.deleteCustomer(c);
	}
}

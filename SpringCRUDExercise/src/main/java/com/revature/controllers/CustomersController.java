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

import com.revature.models.Customers;
import com.revature.services.CustomerService;

@RestController
@RequestMapping(value="/customers")
public class CustomersController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customers> findAllCustomers() {
		return customerService.getCustomers();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Customers findCustomerById(@PathVariable("id") int id) {
		return customerService.getCustomerById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Customers createCustomer(@RequestBody Customers customer) {
		return customerService.createCustomer(customer);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Customers updateCustomer(@RequestBody Customers customer) {
		return customerService.updateCustomer(customer);
	}
	
	@DeleteMapping(consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Customers deleteCustomer(@RequestBody Customers customer) {
		return customerService.deleteCustomer(customer);
	}
}

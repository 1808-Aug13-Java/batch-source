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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Customer;
import com.revature.services.CustomerService;

@RestController
@RequestMapping(value="/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findAllCustomers() {
		return customerService.getCustomers();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer findCreateById(@PathVariable("id") int id) {
		return customerService.getCustomerById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer createCustomer(@RequestBody Customer c) {
		System.out.println(c);
		return customerService.createCustomer(c);
	}
	
	@PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer updateCustomer(@RequestBody Customer c, @PathVariable("id") int id) {
		System.out.println("put request:" + c);
		c.setId(id);
		return customerService.updateCustomer(c);
	}
	
	@DeleteMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer deleteCustomer(@PathVariable("id") int id) {
		Customer c = customerService.getCustomerById(id);
		System.out.println("calling delete");
		return customerService.deleteCustomer(c);
	}
	
	@RequestMapping(method= {RequestMethod.OPTIONS, RequestMethod.TRACE, RequestMethod.PATCH, RequestMethod.HEAD})
	public Customer metadataCall() {
		return null;
	}
}

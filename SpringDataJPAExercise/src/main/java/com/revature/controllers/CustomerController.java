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
	CustomerService customerServ;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findAllCustomers(){
		return customerServ.findAllCustomers();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer findCustomerById(@PathVariable("id") Integer id) {
		return customerServ.findCustomerById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer addCustomer(@RequestBody Customer c) {
		return customerServ.addCustomer(c);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer updateCustomer(@RequestBody Customer c) {
		return customerServ.updateCustomer(c);
	}
	
	@DeleteMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer deleteCustomer(@RequestBody Customer c) {
		return customerServ.deleteCustomer(c);
	}
	
	@DeleteMapping(value="/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer deleteCustomerById(@PathVariable("id") Integer id) {
		return customerServ.deleteCustomerById(id);
	}
}

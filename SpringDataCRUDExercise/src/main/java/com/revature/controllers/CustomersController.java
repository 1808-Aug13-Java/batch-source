package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/customers")
public class CustomersController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customers> findAllCustomers(){
		return customerService.findAllCustomers();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Customers findCustomerById(@PathVariable("id") Long id) {
		return customerService.findCustomerById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Customers addCustomer(@Valid @RequestBody Customers c) {
		return customerService.addCustomer(c);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Customers updateCustomer(@RequestBody Customers c, @PathVariable("id") Long id) {
		c.setCustomerId(id);
		return customerService.updateCustomer(c);
	}
	
	@DeleteMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Customers deleteCustomer(@RequestBody Customers c, @PathVariable("id") Long id) {
		c.setCustomerId(id);
		return customerService.deleteCustomer(c);
	}

}

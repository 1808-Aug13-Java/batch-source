package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.revature.models.Customers;
import com.revature.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customers> findAllCustomers() {
		return customerService.findAllCustomers();
		}
	
	@GetMapping(value= "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Customers findCustomersById(@PathVariable("id")Long id) {
		return customerService.findCustomersById(id);
	}

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Customers addCustomers(@Valid @RequestBody Customers newUser) {
		return customerService.addCustomers(newUser);
		
	}
	
	@PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Customers updateCustomers(@Valid @RequestBody Customers customer, @PathVariable("id") Long id) {
		customer.setId(id);
		return customerService.updateCustomers(customer);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void deleteCustomers(@PathVariable("id") Long id) {
		customerService.deleteCustomers(id);
	}
	
	@RequestMapping(method= {RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.TRACE, RequestMethod.PATCH})
	public Customers metadataCar() {
		return null;
	}

}

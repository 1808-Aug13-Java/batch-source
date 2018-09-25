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

import com.revature.models.CustomerHibernate;
import com.revature.services.CustomerService;

@RestController   	//means that weere saying all these views controller maps to will be ResponseBodies
@RequestMapping("/customer")
//@Controller
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
	public List<CustomerHibernate> findCustomers()
	{
		return customerService.findAllCustomers();
	}
	
	//get customer by id
	@GetMapping(value="/{name}", produces="application/json")
	public CustomerHibernate getCustomerById(@PathVariable("name") String cusName)
	{
		return customerService.findCustomerByName(cusName);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public CustomerHibernate createCustomer(@RequestBody CustomerHibernate customer) 
	{
		return customerService.saveCustomer(customer);
	}
	
	//update customer
	@PutMapping(consumes="application/json", produces="application/json")
	public CustomerHibernate updateCustomer(@RequestBody CustomerHibernate customer) 
	{
		return customerService.updateCustomer(customer);
	}
	//delete customer
	@DeleteMapping(consumes="application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public String deleteCustomer(@RequestBody CustomerHibernate customer)
	{
		customerService.deleteCustomer(customer);
		return "Successfully deleted the customer!";
	}
	
	
	
}

package com.revature.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.models.Customer;
import com.revature.services.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController 
{
	@Autowired
	CustomerService custServe;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Customer> findAllCustomers()
	{
		System.out.println("Get Cust called");
		return custServe.findAllCustomers();
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE, value="/{id}")
	@ResponseBody
	public Customer findCustomer(@PathVariable Integer id)
	{
		return custServe.findCustomerById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Customer addCustomer(@RequestBody Customer u)
	{
		return custServe.addCustomer(u);
	}
	
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Customer updateCustomer(@RequestBody Customer u)
	{
		return custServe.updateCustomer(u);
	}
	
	@DeleteMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Customer deleteCustomer(@RequestBody Customer u)
	{
		return custServe.deleteCustomer(u);
	}
}

package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Customer;
import com.revature.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerCotroller {
	
	@Autowired
	CustomerService custServe;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getCustomers() {
		if (true) throw new RuntimeException();
		return custServe.getAllCustomers();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer getCustomerById(@PathVariable("id") int custId) {
		return custServe.getCustomerById(custId);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Customer addCustomer(@RequestBody Customer cust) {
		return custServe.addCustomer(cust);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateCustomer(@RequestBody Customer cust) {
		custServe.updateCustomer(cust);
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteCustomer(@PathVariable("id") int custId) {
		custServe.deleteCustomerById(custId);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void exceptionTest(Exception ex, HttpServletResponse response) {
		System.out.println("Oops...: " + ex);
		ex.printStackTrace();
		try {response.sendError(500);} catch (IOException e) {}
	}
}









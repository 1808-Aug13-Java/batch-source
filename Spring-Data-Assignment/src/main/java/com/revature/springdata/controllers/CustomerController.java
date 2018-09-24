package com.revature.springdata.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import com.revature.springdata.models.Customer;
import com.revature.springdata.repositories.CustomerRepository;

@RestController
public class CustomerController {
  @Autowired
  CustomerRepository customerRepository;
  
 public Iterable<Customer> findCustomers() {
   return customerRepository.findAll(); 
 } 

 public Customer findCustomer(Long id) {
   return customerRepository.findById(id).get();
 }
}

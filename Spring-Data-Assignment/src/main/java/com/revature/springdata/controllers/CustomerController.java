package com.revature.springdata.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import com.revature.springdata.models.Customer;
import com.revature.springdata.repositories.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {
  @Autowired
  CustomerRepository customerRepository;

  @GetMapping
  public Iterable<Customer> findCustomers() {
    return customerRepository.findAll(); 
  } 

  @GetMapping("/{id}")
  public Customer findCustomer(@PathVariable("id") Long id) {
    return customerRepository.findById(id).get();
  }

  @PostMapping(value="/")
  public void createCustomer(@RequestBody Customer c) {
    customerRepository.save(c);
  }
  @PutMapping(value="/")
  public void updateCustomer(@RequestBody Customer c) {
    customerRepository.save(c);
  }
  @DeleteMapping(value="/{id}")
  public void deleteCustomer(@PathVariable("id") Long id) {
    Customer c = customerRepository.findById(id).get();
    customerRepository.delete(c);
  }
}

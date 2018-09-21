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

import com.revature.models.Customer;
import com.revature.services.UserService;

@RestController
@RequestMapping("/customers")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public List<Customer> findAllUsers(){
		
		return userService.findAllUsers();
		
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Customer findUserById(@PathVariable("id") Long id) {
		return userService.findUserById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Customer addUser(@Valid @RequestBody Customer u) {
		return userService.addUser(u);
	}
	
	@PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Customer updateUser(@Valid @RequestBody Customer u) {
		return userService.updateUser(u);
	}
	
	
	@DeleteMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Customer deleteUser(@RequestBody Customer u, @PathVariable("id") int id) {
		u.setId(id);
		return userService.deleteUser(u);
	}
	
	@RequestMapping(method= {RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.TRACE,RequestMethod.PATCH})
	public Customer metadataCar() {
		
		return null;
	}
	
	

}

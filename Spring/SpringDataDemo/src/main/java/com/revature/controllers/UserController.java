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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.models.User;
import com.revature.services.UserService;


// @RestController implies @Response body on all methods in this class
// "restful api"
@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	// Manage value="/users" above using RequestMapping()
//	@GetMapping(value="/users", produces=MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> findAllUsers(){
		return userService.findAllUsers();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User findUserById(@PathVariable("id") Long id){
		return userService.findUserById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User addUser(@Valid @RequestBody User u) {
		return userService.addUser(u);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User updateUser(@Valid @RequestBody User u) {
		return userService.updateUser(u);
	}
	
	@DeleteMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User deleteUser(@RequestBody User u, @PathVariable("id") Long id) {
		u.setUserId(id);
		return userService.deleteUser(u);
	}
	
//	@RequestMapping(method = {RequestMethod.OPTION, RequestMethod.HEAD, RequestMethod.TRACE, RequestMethod.PATCH})
	
	
	// Doesn't follow uniform interface
//	@GetMapping(value="/login")
//	public User loggedInUser(@RequestParam("user") String user, @RequestParam("pass") String pass) {
//		return userService.login(user, pass);
//	}
}

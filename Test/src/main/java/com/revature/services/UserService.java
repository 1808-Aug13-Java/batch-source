package com.revature.services;

import java.util.List;

import com.revature.models.Customer;

public interface UserService {
	
	public List<Customer> findAllUsers();
	public Customer findUserById(Long id);
	public Customer addUser(Customer newUser);
	public Customer updateUser(Customer user);
	public Customer deleteUser(Customer user);

}

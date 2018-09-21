package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Customer;
import com.revature.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<Customer> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public Customer findUserById(Long id) {
		return userRepo.getOne(id);
	}

	@Override
	public Customer addUser(Customer newUser) {
		return userRepo.save(newUser);
	}

	@Override
	public Customer updateUser(Customer user) {
		return userRepo.save(user);
	}

	@Override
	public Customer deleteUser(Customer user) {
		userRepo.delete(user);
		
		return user;
	}

}

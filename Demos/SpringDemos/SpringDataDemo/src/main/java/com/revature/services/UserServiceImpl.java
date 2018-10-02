package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	public User findUserById(long id) {
		return userRepo.getOne(id);
	}

	public User addUser(User newUser) {
		return userRepo.save(newUser);
	}

	public User updateUser(User user) {
		return userRepo.save(user);
	}

	public User deleteUser(User user) {
		userRepo.delete(user);
		return user;
	}

}

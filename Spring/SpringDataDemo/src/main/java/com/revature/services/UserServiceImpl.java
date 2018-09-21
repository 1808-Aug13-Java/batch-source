package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User findUserById(Long id) {
		return userRepo.getOne(id);
	}

	@Override
	public User addUser(User newUser) {
		return userRepo.save(newUser);
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public User deleteUser(User user) {
		userRepo.delete(user);
		return user;
	}
	
	public User login(String user, String pass) {
		return userRepo.findUserByUsernameAndPassword(user, pass);
	}

}

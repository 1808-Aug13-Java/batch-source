package com.revature.services;

import java.util.List;

import com.revature.models.User;

public interface UserService {
	public List<User> findAllUsers();
	public User findUserById(long id);
	public User addUser(User newUser);
	public User updateUser(User user);
	public User deleteUser(User user);
}

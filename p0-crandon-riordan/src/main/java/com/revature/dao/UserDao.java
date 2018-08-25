package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface UserDao {
	public List<User> getAllUsers();
	public User getUserById(int id);
	public int createUser(User user);
	public int updateUser(User user);
	public int deleteUserById(User user);
}

package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface UserDao {
	public User getUserById(int id);
	public User getUserByName(String name);
	public int createUser(User user);
	public int updateUser(User user);
	public int hideUserById(User user);
	public boolean isUsernameUnique(User user);
	public boolean isUserEmailUnique(User user);
}

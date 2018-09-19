package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDao {
	User getUserById(String id);
	String createUser(User user);
	List<User> getUsers();
}

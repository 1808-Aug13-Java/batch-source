package com.revature.dao;

import com.revature.models.User;

public interface UserDao {
	User getUserById(String id);
	String createUser(User user);
	
}

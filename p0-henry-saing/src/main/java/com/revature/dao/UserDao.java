package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface UserDao {
	public List<User> getUsers();
	public int createUser(String username, String password);
	public boolean validateUser(String username, String password);

}

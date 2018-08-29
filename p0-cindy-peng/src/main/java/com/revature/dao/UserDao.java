package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDao {
	
	//methods
	//storing the users for printing..i think
	public List<User> getUsers(); 	//returns table info in a list. Kind of like a cursor
	//all our CRUD operations
	//this is like select, where. returns the user 
	public User getUserByUsername(String username);	//select FIXME still only user not email
	public int createUser(User user);
	public int updateUser(User user);
	public int deleteUser(User user);
	
	public void makeWithdrawal(String username, float minus);
	
}

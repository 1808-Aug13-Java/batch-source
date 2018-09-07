package com.revature.bank.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

import com.revature.model.User;


public interface UserDAO {

	public Set<User> getUsers();
	public User getUserByName(String name);
	public User getUserByName(String name, Connection con);
	public int createUser(User user);
	public int createUser(User user, Connection con);
	public int updateAccount(User user);
	public int updateAccount(User user, Connection con);
	public int deletUserByName(String name);	
	
}

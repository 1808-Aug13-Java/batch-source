package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface UserDao {

	
	public List<User> getUsers();
	public User getUsersByUsername(String username);
	public int createUser(User username);
	public int updateUser(User username);
	public int deleteUsers(String username);
	public void makeDeposit(String username, float depositAmount);
	public void makeWithdrawal(String username, float withdrawalAmount);
//	public void getBalance(String username, float balance);
	
}

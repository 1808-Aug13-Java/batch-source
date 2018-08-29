package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.BankUsers;

public interface BankUsersDao {

	public BankUsers getUserById(int id);
	public BankUsers getUserByName(String name);
	public int createUser(BankUsers user);
	public int updateUser(BankUsers user);
	public int hideUserById(BankUsers user);
	public boolean isUsernameUnique(BankUsers user);
	public boolean isUserEmailUnique(BankUsers user);
}

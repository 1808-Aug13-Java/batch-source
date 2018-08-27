package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Login;

public interface LoginDao {
	public List<Login> getLogins();
	public Login getLoginById(String id);
	public Login getLoginById(String id, Connection con);
	public int createLogin(Login login);
	public int updateLogin(Login login);
	public int deleteLoginById(String id); 
}

package com.revature.dao;

import java.util.List;

import com.revature.models.Login;

public interface LoginDao {
	public Login getLoginByUser(String user);
	public List<Login> getLogins();
}

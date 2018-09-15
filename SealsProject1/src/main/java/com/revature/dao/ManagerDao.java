package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Manager;

public interface ManagerDao {
	
	public Manager getManagerById(int id);
	public List<Employee> getEmployeesByManagerId(int id);
	public boolean isMatchPassword(String inputUsername, String inputPwd);
	public boolean isMatchUsername(String inputUsername);
	public Manager getManagerByUsername(String username);

}

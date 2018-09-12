package com.revature.dao;

import java.util.List;

import com.revature.model.Zukemployee;

public interface ZukemployeeDao {
	
	public List<Zukemployee> getEmployees();
	public List<Zukemployee> getManagers();
	public List<Zukemployee> getNonManagersOnly();
//	public Zukemployee getPasswordById(int id);
	public Zukemployee getEmployeeById(int id);
	public Zukemployee getEmployeeByUsername(String username);
	public Zukemployee getEmployeeByPassword(String password);
	public int updateEmployee(Zukemployee employee); // An Employee can update their information 

}

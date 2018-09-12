package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {

	public boolean validateUser(String username, String password);
	public List <Employee>getAllEmployees();
	public boolean updateEmployee(int empId, String name, String username, String password, String email);
}

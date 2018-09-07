package com.revature.dao;

import java.util.List;

import com.revature.models.Employees;

public interface EmployeesDao {
	int createEmployee(Employees employee);
	Employees getEmployeeByUsername(String username); //instead of email address
	Employees getEmployeesById(int id);
	Employees getEmployeesByName(String name);
	List<Employees> getAllEmployees();
	
}

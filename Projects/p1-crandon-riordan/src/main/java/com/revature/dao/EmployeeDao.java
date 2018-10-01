package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {
	int createEmployee(Employee employee);
	int updateEmployee(Employee employee);
	Employee getEmployeeByEmail(String email);
	Employee getEmployeeByUsername(String username);
	Employee getEmployeeById(int id);
	List<Employee> getAllEmployees();
	int createManager(Employee employee);
}

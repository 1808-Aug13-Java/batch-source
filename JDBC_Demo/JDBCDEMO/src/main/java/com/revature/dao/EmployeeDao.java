package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {

	
	public Employee getEmployeeById(int id);
	public List<Employee> getEmployees();
	public int createEmployee(Employee employee);
	public int updateEmployee(Employee employee);
	public int deleteEmployeeById(int id);
	
}

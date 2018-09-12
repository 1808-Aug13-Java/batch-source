package com.revature.dao;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {
	
	public List<Employee> getEmployees();
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByEmail(String email);
	public int createEmployee(Employee employee);
	public int deleteEmployeeById(int id);
	public int updateEmployee(int id, String name, String user, String privateInfo);
}

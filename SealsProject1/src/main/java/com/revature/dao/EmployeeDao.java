package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDao {
	
	public List<Employee> getEmployees();
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByUsername(String username);
	public int createEmployee(Employee employee);
	public int updateEmployee(Employee employee);
	public int deleteEmployeeById(int id);
	public boolean isMatchUsername(String userInput);
	public boolean isMatchPassword(String userInput, String userPwd);

}

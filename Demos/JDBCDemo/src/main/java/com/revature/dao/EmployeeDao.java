package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.model.Employee;

public interface EmployeeDao {
	public List<Employee> getEmployees();
	public Employee getEmployeeById(int id);
	public Employee getEmployeeById(int id, Connection con);
	public int createEmployee(Employee employee);
	public int updateEmployee(Employee employee);
	public int deleteEmployee(Employee employee);
}

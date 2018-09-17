package com.chandrika.dao;

import java.sql.Connection;
import java.util.List;

import com.chandrika.model.Employee;

public interface EmployeeDao {
	public List<Employee> getEmployees();
	public Employee getEmployeeById(int id);
	Employee getEmployeeById(int id, Connection con);
	public int createEmployee(Employee employee);
	Employee getEmployeeByUsername(String username);
}

package com.revature.dao;

import java.sql.Connection;
import java.util.List;

import com.revature.models.Employee;


public interface EmployeeDao {

	public List<Employee> getEmployees();
	public List<Employee> getEmployeesByManagerId(int managerId);
	public Employee getEmployeeByUserName(String userName);
	public Employee getEmployeeById(int id);
	public int createEmployee(Employee employee);
	public int updateEmployee(Employee employee);
	public int deleteEmployeeById(int id);
	public int createEmployee(Employee employee, Connection con);
	public int updateEmployee(Employee employee, Connection con);
	public int deleteEmployeeById(int id, Connection con);
}
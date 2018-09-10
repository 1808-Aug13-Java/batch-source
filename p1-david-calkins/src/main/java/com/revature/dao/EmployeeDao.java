package com.revature.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Manager;

public interface EmployeeDao {
	
	public Employee getEmployeeById(long id, Connection con) throws SQLException;
	
	public Employee getEmployeeByUsername(String username, Connection con) throws SQLException;
	
	
	public List<Employee> getAllEmployees(Connection con) throws SQLException;
	
	public List<Employee> getEmployeesByManager(Manager man, Connection con) throws SQLException;
	
	
	public long insertEmployee(Employee emp, Connection con) throws SQLException;
	
	public int updateEmployee(Employee emp, Connection con) throws SQLException;
	
	
}

package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDaoInterface {

	public List<Employee> getAllEmployees();
	public List<Employee> getAllManagers();
	public List<Employee> getNonManagers();
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByUsername(String user);
	public boolean matchPassword(String user, String pass);
	public boolean verifyManager(String user, String pass);
	public int addEmployee(Employee emp);
	public int addManager(Employee emp);
	public int updatePass(String user, String pass);
	public int updateEmail(String user, String email);
	public int updatePhone(String user, long phone);
	public int updateSecond(String user, long second);
	public int updateState(String user, String state);
	public int updateIsManager(String user, boolean man);
	public int updateInfo(String sql);
	
	public int deleteEmployee(Employee emp);
	public int deleteEmployee(String user);
	public int deleteEmployee(int id);
	public int delete(String sql);
}

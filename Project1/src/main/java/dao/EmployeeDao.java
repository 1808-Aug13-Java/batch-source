package dao;

import java.sql.Connection;
import java.util.List;


public interface EmployeeDao
{
	List<Employee> GetEmployeeList(Connection con);
	List<Employee> GetManagerList(Connection con);
	List<Employee> GeNonManagerList(Connection con);
	Employee GetEmployeeById(int id, Connection con);
	
	Employee GetEmployeeByCredentials(String user, String pass, Connection con);
	
	boolean checkUserName(String name, Connection con);
	
	public int createEmployee(Employee emp, String pw, Connection con);
	public int updateEmployee(Employee emp, Connection con);
	
	public int deleteEmployee(Employee emp, Connection con);
}

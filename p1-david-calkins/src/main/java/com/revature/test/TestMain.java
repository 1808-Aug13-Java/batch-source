package com.revature.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.util.DBConnectionUtil;

public class TestMain {

	public static void main(String[] args) throws IOException, SQLException {
		System.out.println("Getting Connection...");
		
		// Autoclose that connection
		try (Connection con = DBConnectionUtil.getConnection()) {
			// Get Dao
			EmployeeDao empDao = new EmployeeDaoImpl();
			con.setAutoCommit(false);
			
			Employee temp = null;
			
			System.out.println("Querying...");
			List<Employee> emps = empDao.getAllEmployees(con);
			
			for (Employee e : emps) {
				System.out.println(e);
			}
			
			System.out.println("Getting by ID...");
			System.out.println(empDao.getEmployeeById(5, con));
			
			System.out.println("Bad ID returns null? " + (empDao.getEmployeeById(15, con)));
			
			System.out.println("By Manager ID...");
			Manager man = new Manager();
			man.setId(5);
			emps = empDao.getEmployeesByManager(man, con);
			for (int i=0; i<emps.size(); i++) {
				System.out.println(emps.get(i));
			}
			
			System.out.println("Inserting Employee...");
			temp = new Employee(0, "Dennis", "DennisTheMeanice", "passwordHash");
			empDao.insertEmployee(temp, con);
			System.out.println("New Employee: " + empDao.getEmployeeById(temp.getId(), con));
			
			System.out.println("Updating Employee...");
			temp.setName("Bieber");
			temp.setUsername("JustinIsBustin");
			temp.setPasswordHash("slightlyBetterPassword");
			empDao.updateEmployee(temp, con);
			System.out.println("Updated Employee: " + empDao.getEmployeeById(temp.getId(), con));
			
			// Don't save any of the changes done in this test. 
			con.rollback();
		}
	} // end of main

}

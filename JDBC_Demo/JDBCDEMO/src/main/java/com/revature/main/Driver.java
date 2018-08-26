package com.revature.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.DepartmentDao;
import com.revature.dao.DepartmentDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.LocationDao;
import com.revature.dao.LocationDaoImpl;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Location;
import com.revature.util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//test the connection and ensure that we can acces our db
		
		try {
			//Connection con = ConnectionUtil.getHardCodedConnection();
			Connection con = ConnectionUtil.getConnection();
			System.out.println(con.getMetaData().getDriverName());
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		EmployeeDao edi=new EmployeeDaoImpl();
//		List<Employee> allEmployees = edi.getEmployees();
//		System.out.println(allEmployees);
//		for (Employee e: allEmployees) {
//			System.out.println(e);
//		}
		
//		LocationDao ldi = new LocationDaoImpl();
//		List<Location> allLocations = ldi.getLocations();
//		System.out.println(allLocations);
//		for (Location l:allLocations) {
//			System.out.println(l);
//		}
		
		//departmentDao here
		
		DepartmentDao ddi = new DepartmentDaoImpl();
//		List<Department> allDepartments = ddi.getDepartments();
//		System.out.println(allDepartments);
//		for (Department d:allDepartments) {
//			System.out.println(d);
//		}
		
		
		
	}

}

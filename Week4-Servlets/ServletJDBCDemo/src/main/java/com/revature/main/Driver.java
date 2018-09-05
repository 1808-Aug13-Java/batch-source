package com.revature.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.*;
import com.revature.model.*;
import com.revature.util.ConnectionUtil;

public class Driver {

	public static void main(String[] args) {
		
		/*
		try {
			Connection con = ConnectionUtil.getConnection();
			System.out.println(con.getMetaData().getDriverName());
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		*/
		
		
		/*
		EmployeeDao edi = new EmployeeDaoImpl();
		List<Employee> allEmployees = edi.getEmployees();
		for(Employee e: allEmployees) {
			System.out.println(e);
		}
		*/
		
		
		DepartmentDaoImpl ddi = new DepartmentDaoImpl();
//		Department d = ddi.getDepartmentById(1);
//		System.out.println(d);
		List<Department> departments = ddi.getDepartments();
		for(Department d : departments) {
			System.out.println(d);
		}
//		System.out.println(ddi.createDepartment(new Department("Janitorial",70000)));
//		Department d = ddi.getDepartmentById(9);
//		d.setMonthlyBudget(80000);
//		System.out.println(ddi.updateDepartment(d));
//		System.out.println(ddi.deleteDepartmentById(4));
//		ddi.increaseBudget(9, 1000);
		
//		LocationDao ldi = new LocationDaoImpl();
//		System.out.println(ldi.getLocationById(3));
//		List<Location> locations = ldi.getLocations();
//		for(Location l : locations) {
//			System.out.println(l);
//		}

		
	}

}

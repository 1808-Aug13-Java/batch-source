package com.revature.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.*;
import com.revature.dao.*;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.util.connectionUtil;

public class Driver {

	public static void main(String[] args) throws IOException {
		
		/*
		try {
			Connection con = connectionUtil.getConnection();
			System.out.println(con.getMetaData().getDriverName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
		
//		DepartmentDao ddi = new DepartmentDaoImpl();
//		Department d = ddi.getDepartmentById(1);
//		System.out.println(d);
//		
//		
//		EmployeeDao edi = new EmployeeDaoImpl();
//		List<Employee> allEmployees = edi.getEmployees();
//		for(Employee e: allEmployees) {
//			System.out.println(e);
//		}
		
//		LocationDao ldi = new LocationDaoImpl();
//		List<Location> allLocations = ldi.getLocations();
//		for(Location l: allLocations) {
//			System.out.println(l);
//		}
//		
//		DepartmentDao eid = new DepartmentDaoImpl();
//		Department f = eid.getDepartmentById(1);
//		System.out.println(f);
//		
//		DepartmentDao ddi = new DepartmentDaoImpl();
//		List<Department> allDepartments = ddi.getDepartments();
//		for(Department d: allDepartments) {
//			System.out.println(d);
//		}
		
		//ddi.createDepartment(new Department(7, "Janitorial", 7000));
		
		DepartmentDaoImpl ddi = new DepartmentDaoImpl();
//		
		Department d = ddi.getDepartmentById(27);
//		d.setMonthlyBudget(8000);
//		System.out.println(ddi.updateDepartment(d));
		System.out.println(ddi.deletebDepartmentById(27));
		ddi.increaseBudget(6, 300);
		
		
		
	}

}

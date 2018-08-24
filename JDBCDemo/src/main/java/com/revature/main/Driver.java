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
		
		
		EmployeeDao edi = new EmployeeDaoImpl();
		List<Employee> allEmployees = edi.getEmployees();
		for(Employee e: allEmployees) {
			System.out.println(e);
		}
		
		
//		DepartmentDao ddi = new DepartmentDaoImpl();
//		Department d = ddi.getDepartmentById(1);
//		System.out.println(d);
		
//		LocationDao ldi = new LocationDaoImpl();
//		System.out.println(ldi.getLocationById(3));

	}

}

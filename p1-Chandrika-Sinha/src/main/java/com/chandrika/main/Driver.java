package com.chandrika.main;

import java.util.List;

import com.chandrika.dao.*;
import com.chandrika.model.*;

public class Driver {

	public static void main(String[] args) {
		/*
		try {
			Connection con = ConnectionUtil.getConnection();
			System.out.println(con.getMetaData().getDriverName());
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		RequestDao rdi = new RequestDaoImpl();
		// List<Request> allRequests = rdi.getRequests();
		List<Request> allRequests = rdi.getRequestsByEmployeeId(5);
		for (Request r: allRequests) {
			System.out.println(r);
		}
		// Request r = rdi.getRequestById(4);
		// System.out.println(r); */
		// */
		/*
		ManagerDao mdi = new ManagerDaoImpl();
		List<Manager> allManagers = mdi.getManagers();
		System.out.println(allManagers.size() + " managers");
		for (Manager m: allManagers) {
			System.out.println(m);
		}
		Manager m = mdi.getManagerById(2);
		System.out.println(m);
		// */
		/*
		EmployeeDao edi = new EmployeeDaoImpl();
		List<Employee> allEmployees = edi.getEmployees();
		System.out.println(allEmployees.size() + " managers");
		for (Employee e: allEmployees) {
			System.out.println(e);
		}
		Employee e = edi.getEmployeeById(2);
		System.out.println(e);
		// */
	}
}

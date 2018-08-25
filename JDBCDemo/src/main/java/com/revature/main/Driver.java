package com.revature.main;

import java.util.List;

import com.revature.dao.*;
import com.revature.model.*;

public class Driver {

	public static void main(String[] args) {
		
//		EmployeeDao edi = new EmployeeDaoImpl();
//		List<Employee> allEmployees = edi.getEmployees();
//		
//		for(Employee e: allEmployees) {
//			System.out.println(e);
//		}
		
		DepartmentDaoImpl ddi = new DepartmentDaoImpl();
//		Department d = ddi.getDepartmentById(1);
//		System.out.println(d);
//		
//		LocationDao ldi = new LocationDaoImpl();
//		List<Location> locations = ldi.getLocations();
//		for(Location l : locations) {
//			System.out.println(l);
//		}
		// System.out.println(ddi.createDepartment(new Department("Janitorial", 70000)));
//		Department d = ddi.getDepartmentById(47);
//		d.setMonthlyBudget(80000);
//		System.out.println(ddi.updateDepartment(d));
//		System.out.println(ddi.deleteDepartmentById(4));
		ddi.increaseBudget(47, 500);
	}

}

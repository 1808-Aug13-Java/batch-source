package com.revature.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

//import org.apache.log4j.BasicConfigurator;

public class MainDriver {

	public static void main(String[] args) {
		
		try {
			Connection con = ConnectionUtil.getConnection();
			System.out.println(con.getMetaData().getDriverName());
		} catch (SQLException |IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EmployeeDao edi = new EmployeeDaoImpl();
		
		List<Employee> allEmployees = edi.getEmployees();
		for(Employee e: allEmployees) {
			System.out.println(e);
		}
		
		/*
		Employee e = new Employee();
		e.setName("John Smith");
		e.setUsername("johnsmith");
		e.setPwd("password2");
		e.setManagerId(501);
		*/
		
		//System.out.println(edi.createEmployee(new Employee(0, "Pickles McGee", "picklesmcgee", "password3", 504)));
		//System.out.println(e);
		//System.out.println(edi.updateEmployee(new Employee(121, "Justin Timberlake", "justintimberlake", "password3", 501)));
		
		/*
		ManagerDao mdi = new ManagerDaoImpl();
		List<Employee> byManagerId = mdi.getEmployeesByManagerId(501);
		for(Employee e: byManagerId) {
			System.out.println(e);
		}
		*/
		ReimbursementDao rdi = new ReimbursementDaoImpl();
		/*
		List<Reimbursement> allRequests = rdi.getReimbursement();
		for(Reimbursement r : allRequests) {
			System.out.println(r);
		}
		*/
				
		//System.out.println(rdi.createReimbursement(new Reimbursement(0, 505, 107, 0, 121.21, "Lodging")));
		System.out.println(rdi.updateReimbursement(new Reimbursement(1042, 504, 102, 1, 14.75, "SUPPLIES")));
		/*
		List<Reimbursement> byStatus = rdi.getReimbursementByManagerId(504);
		for(Reimbursement r : byStatus) {
			System.out.println(r);
		}
		*/
	}
}

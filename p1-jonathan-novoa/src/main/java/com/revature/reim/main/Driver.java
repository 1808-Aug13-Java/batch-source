package com.revature.reim.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.revature.reim.dao.EmployeeDaoImpl;
import com.revature.reim.dao.ManagerDaoImpl;
import com.revature.reim.util.ConnectionUtil;
import com.revature.reim.util.ManagerListHelper;

public class Driver {

	public static void main(String[] args) {
//		try {
//			Connection con = ConnectionUtil.getConnection();
//			System.out.println(con.getMetaData().getDriverName());
//		} catch (SQLException | IOException e) {
//			e.printStackTrace();
//		}
//		Database connection successful for far
		EmployeeDaoImpl emp= new EmployeeDaoImpl();
		ManagerDaoImpl manEmp= new ManagerDaoImpl();
		
//		System.out.println(emp.viewProfile(1));
//		System.out.println(emp.logIn("smartguy@hotmail.com","pass123"));	
//		System.out.println(emp.submitReimbursement(2, 3434.99)); 
//		System.out.println(emp.viewReimbursments(2,3));
//		System.out.println(emp.changeProfile(1, "pass123"));
//		System.out.println(manEmp.viewAllEmployees(3));
//		System.out.println(manEmp.viewAllRequest(1));
//		System.out.println(manEmp.getReimbursementsByEmp(2));
//		System.out.println(manEmp.resolveRequest(2, 4, 2, 2));
//		System.out.println(manEmp.createEmployee("Thomas", "Edison", "tedison@gmail.con", "asdf", 0));
//		System.out.println(emp.getEmpId("test@aol.com"));
//		System.out.println(ManagerHelper.getList(3));
//		System.out.println(emp.changeProfile(5, "asdf", "Thomas", "Edison", "tedison@gmail.com"));
	}
}

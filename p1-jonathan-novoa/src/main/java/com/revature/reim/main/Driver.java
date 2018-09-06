package com.revature.reim.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.revature.reim.dao.EmployeeDaoImpl;
import com.revature.reim.util.ConnectionUtil;

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
		
//		System.out.println(emp.viewProfile(1));
//		System.out.println(emp.logIn("smarguy@hotmail.com","pass123"));	
//		System.out.println(emp.submitReimbursement(2, 3000.49)); 
//		System.out.println(emp.viewPendingReimbursments(1));
//		System.out.println(emp.viewResolvedReimbursements(1));
//		System.out.println(emp.changeProfile(1, "admin"));
		
		
		
	}
}

package com.revature.main;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import com.revature.dao.*;
import com.revature.model.*;
import com.revature.util.ConnectionUtil;

import oracle.sql.DATE;

/**
 * @author Nozuko
 *
 */
public class Driver {

	/**
	 * @param args
	 * where I  will be testing my DAOs to make sure I am able to access my database
	 */
	public static void main(String[] args) {

//		ZukemployeeDao zdi = new ZukemployeeDaoImpl();
//		List<Zukemployee> allEmployees = zdi.getEmployees();
//		for(Zukemployee e: allEmployees) {
//			System.out.println(e);
//		}
		
//		ZukemployeeDao zdi = new ZukemployeeDaoImpl();
//		Zukemployee anEmployee = zdi.getEmployeeById(5);
//		System.out.println(anEmployee);
		
//		ReimbursementDao rdi = new ReimbursementDaoImpl();
//		List<Reimbursement> allReimbursements = rdi.getAllRequests();
//		for(Reimbursement r: allReimbursements) {
//			System.out.println(r);
//		}
		
//		ReimbursementDao rdi = new ReimbursementDaoImpl();
//		List<Reimbursement> allReimbursements = rdi.getAllPendingRequests();
//		for(Reimbursement r: allReimbursements) {
//			System.out.println(r);
//		}
		
//		ReimbursementDao rdi = new ReimbursementDaoImpl();
//		List<Reimbursement> allReimbursements = rdi.getPendingRequestsById(2);
//		for(Reimbursement r: allReimbursements) {
//			System.out.println(r);
//		}
		
//		ReimbursementDao rdi = new ReimbursementDaoImpl();
//		List<Reimbursement> allReimbursements = rdi.getAllResolvedRequests();
//		for(Reimbursement r: allReimbursements) {
//			System.out.println(r);
//		}
		
		ReimbursementDao rdi = new ReimbursementDaoImpl();
		BigDecimal amt = new BigDecimal(50.50);
		//@SuppressWarnings("deprecation")
		//Date date = new Date (2018, 9, 9);
		
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, 2018);
//		cal.set(Calendar.MONTH, Calendar.AUGUST);
//		cal.set(Calendar.DAY_OF_MONTH, 15);
//		Date dateRepresentation = cal.getTime();
		
		Reimbursement reimbursement = new Reimbursement("I love money", "Pending", amt, 6, 1);
		System.out.println(rdi.createRequest(reimbursement));

	} // end main method

}

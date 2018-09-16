package com.revature.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.dao.ZukemployeeDao;
import com.revature.dao.ZukemployeeDaoImpl;
import com.revature.model.Zukemployee;

public class RequestHelper {

	// provides a layer so the servlets don't have logic in them. best practice
	public static String process(HttpServletRequest request) {
		switch(request.getParameter("destination")) {
		case "directory":
			return "directory"; // no need for breaks because of return statements
		case "new":
			return "new";
		case "request":
			return "request";
		case "allpending":
			return "allpending";
		case "allresolved":
			return "allresolved";
		case "updaterequest":
			return "updaterequest";
		default:
			return "home";
		}
	}
	
	public static String loggingIn(HttpServletRequest request, String user, String pass) {
		ZukemployeeDao emp1 = new ZukemployeeDaoImpl();
		Zukemployee employeeFromEnteredUsername = emp1.getEmployeeByUsername(user);
		
		ZukemployeeDao emp2 = new ZukemployeeDaoImpl();
		Zukemployee employeeFromEnteredPassword = emp2.getEmployeeByPassword(pass);
		
		if (employeeFromEnteredUsername.getId() > 0 && employeeFromEnteredUsername.equals(employeeFromEnteredPassword)) {
			// can proceed to login successfully
			// but first must check if they are manager or employee for correct portal
			
//			System.out.println("Employee from entered username  "+ employeeFromEnteredUsername);
//			System.out.println("Employee from entered password  "+ employeeFromEnteredPassword);
			
			ZukemployeeDao managerCheck = new ZukemployeeDaoImpl();
			List<Zukemployee> managersList = managerCheck.getManagers();
			
			if (managersList.contains(employeeFromEnteredUsername)) {
				// go to manager home
				return "managerHome";
			} else {
				// go to employee home
				return "employeeHome";
			}
		} else {
			// fail
			return "login";
		}	
	}
}

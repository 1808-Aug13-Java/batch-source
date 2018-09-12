package com.revature.util;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Employee;

public class RequestHelper {
	
	private static Logger log = Logger.getRootLogger();
	
	public static String validateLogin(String email, String password, HttpSession session) {
		String destination = "";
		
		try {
			EmployeeDao ed = new EmployeeDaoImpl();
			Employee myEmployee = ed.getEmployeeByEmail(email);
			
				//Check if passwords match
				if(ed.getEmployeeByEmail(email).getPrivateInfo().equals(password))
				{
					session.setAttribute("employeeId", myEmployee.getId());
					session.setAttribute("name", myEmployee.getName());
					session.setAttribute("username", myEmployee.getUsername());
					session.setAttribute("private", myEmployee.getPrivateInfo());
	
					//Check if they are a manager
					if(ed.getEmployeeByEmail(email).getIsManager().equalsIgnoreCase("y")) 
					{
						//Login to the manager page
						destination = "managerHome";
					}
					else
					{
						//Login to the employee page
						destination = "employeeHome";
					}
				}
				else
				{
					//Incorrect password!
					destination = "login";
				}
		} catch(NullPointerException e){
			log.error(e);
			destination = "login";
		}
		return destination;
	}
	
	public static String logout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		
		return "login";
	}

}

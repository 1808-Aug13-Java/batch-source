package com.revature.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmpDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;

public class SessionHelper {

	

	public void addIsManagerToSessionByEmail(String email, HttpSession session) {
		EmployeeDao ed = new EmpDaoImpl();
		Employee employee = ed.getEmployeeByEmail(email);
		session.setAttribute("isManager", employee.getIsManager());
	}
	
	public void addInvalidLoginToSession(HttpSession session) {
		session.setAttribute("login", 1);
	}
	
	public void removeLoginFromSession(HttpSession session) {
		session.removeAttribute("login");
	}
	
	public boolean checkValidUser(HttpServletRequest request) {
		boolean foundUser = false;
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("email") != null) {
			foundUser = true;
		}
		return foundUser;
	}

}

package com.revature.util;

import javax.servlet.http.HttpSession;

import com.revature.dao.EmployeesDao;
import com.revature.dao.EmployeesDaoImpl;
import com.revature.models.Employees;

public class SessionManager {
	
	public void createSessionForManagerAccounts(String username, HttpSession session) {
		EmployeesDao ed = new EmployeesDaoImpl();
		Employees employee = ed.getEmployeeByUsername(username);
		session.setAttribute("isManager", employee.getIsManager());
	}
}

package com.revature.util;

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

}

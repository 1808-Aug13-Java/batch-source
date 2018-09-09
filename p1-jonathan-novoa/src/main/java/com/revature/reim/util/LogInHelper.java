package com.revature.reim.util;

import javax.servlet.http.HttpSession;

import com.revature.reim.dao.EmployeeDaoImpl;

public class LogInHelper {
	
	public static int mainLogIn(String uname, String pass) {
		EmployeeDaoImpl emp= new EmployeeDaoImpl();
		return emp.logIn(uname, pass);
	}
	
}

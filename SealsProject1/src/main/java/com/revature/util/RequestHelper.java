package com.revature.util;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
	
	public static String process(HttpServletRequest request) {
		switch(request.getParameter("destination")) {
		case "manager":
			return "managerlogin";
		case "employee":
			return "employeelogin";
		default:
			return "home";
		}
	}

}
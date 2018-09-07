package com.revature.util;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.EmployeesDaoImpl;
import com.revature.models.Employees;

public class LoginAccountManager {

	public String determineAccountType(HttpServletRequest request) {

		Employees e = new EmployeesDaoImpl().getEmployeeByUsername(request.getParameter("username"));

		// Determine if user is valid, regardless of account type
		if (e.getId() == 0) {
			return "login";
		}

		String password = request.getParameter("password");

		// not sure if this actually works
		if (password.equals(e.getPassword())) {
			// regular employee
			if (e.getIsManager() == 0) {
				return "employeehome";
				// manager
			} else if (e.getIsManager() == 1) {
				return "managerhome";
			}

		}

		return "login";

	}
}

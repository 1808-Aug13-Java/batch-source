package com.revature.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmpDaoImpl;
import com.revature.models.Employee;

public class LoginHelper {

	public String process(HttpServletRequest request) {
		// get emp by email
		Employee e = new EmpDaoImpl().getEmployeeByEmail(request.getParameter("email"));
		if(e.getId() == 0) {
			// not a valid user
			HttpSession session = request.getSession();
			SessionHelper sh = new SessionHelper();
			sh.addInvalidLoginToSession(session);
			return "login";
		}
		Hasher hasher = new Hasher();
		String password = request.getParameter("password").replace("\n", "").replace("\r", "");
		String hashedPassword = hasher.getHashPassword(password);
		String empPassword = e.getPassword().replace("\n", "").replace("\r", "");
		// if authentication successfull
		if(hashedPassword.equals(empPassword)) {
			
			if (e.getIsManager() == 0) {
				// not a manager
				return "employeeHome";
			} else if (e.getIsManager() == 1) {
				// is a manager
				return "managerHome";
			}
		}
		
		return "login";
		
		
	}

}

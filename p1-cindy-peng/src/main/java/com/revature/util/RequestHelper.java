package com.revature.util;

import com.revature.models.Staff;

public class RequestHelper {

	public static String getLoginHomepageRedirect(Staff staff)
	{
		switch (staff.getStaffRole())
		{
		case "MANAGER":						//try "cindy" "password"
			//redirect to manager homepage
			return "manager_home";  //manager_home is the name of the servlet url-path
		case "EMPLOYEE":					//try "bax" "password"
			//redirect to employee homepage
			return "employee_home";
		case "BOTH":						//try "amy" "password"
			//redirect to intermediate page with links to manager or employee homepage
			return "intermediate_home";
		default:
			return "login"; //go back to the login page i guess
		}			
	} // getLoginHomepageRedirect()
	
}

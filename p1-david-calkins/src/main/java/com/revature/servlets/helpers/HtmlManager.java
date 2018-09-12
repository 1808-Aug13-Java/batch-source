package com.revature.servlets.helpers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.util.DBConnectionUtil;
import com.revature.util.LogUtil;

public class HtmlManager {
	
	/**
	 * Routes the user to the corresponding page based on if they are a manager
	 * or an employee. 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException 
	 */
	public static void routePage(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException 
	{
		// Attempt to get the employee in this session. 
		Employee emp = ManagerManager.getEmployeeFromSession(request, response);
		
		// If this is actually a manager, show the manager page. 
		if (ManagerManager.isManager(emp)) {
			RequestDispatcher rd = request.getRequestDispatcher("Views/ManagerPage.html");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("Views/EmployeePage.html");
			rd.forward(request, response);
		}
	} // end of routePage
	
	
} // end of class HtmlManager



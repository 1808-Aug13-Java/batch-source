package com.revature.servlets.helpers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

public class ManagerManager {
	
	/** 
	 * Returns an employee object associated with a manager. Note: this might 
	 * be manager object. Should use provided helper method to check. 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public static Employee getEmployeeFromSession(HttpServletRequest request, 
			HttpServletResponse response) throws IOException 
	{
		EmployeeDao empDao = new EmployeeDaoImpl();
		ManagerDao manDao = new ManagerDaoImpl();
		
		String username = (String) request.getSession(false).getAttribute("username");
		if (username == null) {
			LogUtil.logDebug("There was an invalid session passed to routePage");
			response.sendError(500);
			throw new IOException("There was an invalid session passed to routePage");
		}
		
		Employee emp = null;
		Manager man = null;
		
		try (Connection con = DBConnectionUtil.getConnection()) {
			emp = empDao.getEmployeeByUsername(username, con);
			
			// If there wasn't an employee for the session, it is invalid
			if (emp == null) {
				LogUtil.logDebug("Invalid Session");
				response.sendError(500);
				throw new IOException ("Invalid Session");
			}
			
			// Attempt to see if this is a manager. 
			man = manDao.tryCastManager(emp, con);
			
			// If is a manager, return as a manager. 
			if (man != null) {
				emp = man;
			}
		} catch (SQLException e) {
			LogUtil.logDebug("SQLError");
			response.sendError(500);
			throw new IOException(e);
		}
		
		return emp;
	} // end of getEmployeeFromSession
	
	
	
	public static boolean isManager(Employee emp) {
		return emp instanceof Manager;
	}
	
	
} // end of class ManagerManager





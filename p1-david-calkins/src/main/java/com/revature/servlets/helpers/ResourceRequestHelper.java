package com.revature.servlets.helpers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.util.DBConnectionUtil;
import com.revature.util.LogUtil;

public class ResourceRequestHelper {
	
	
	/**
	 * This string represents the expected parameter of a resource request
	 */
	public static final String RESOURCE_REQUEST = "resourceRequest";
	
	
	private static final String LOGOUT = "logout";
	private static final String PENDING = "pending";
	
	public static boolean isResourceRequest(HttpServletRequest request) {
		return request.getParameter(RESOURCE_REQUEST) != null;
	}
	
	
	public static void routeResource(HttpServletRequest request, 
			HttpServletResponse response) throws IOException 
	{
		// If the request doesn't have a session, throw a 403 as there shouldn't 
		// be any access to the resources. 
		if (request.getSession(false) == null) {
			LogUtil.logDebug("Attempted Forbidden Access");
			response.sendError(403);
			return;
		}
		
		// Get Appropriate Dao's 
		EmployeeDao empDao = new EmployeeDaoImpl();
		ManagerDao manDao = new ManagerDaoImpl();
		ReimbursementDao remDao = new ReimbursementDaoImpl();
		
		// Get the username from the request
		String username = (String) request.getSession(false).getAttribute("username");
		
		// Get the print writer to the client response
		PrintWriter pw = response.getWriter();
		
		// Used to map json
		ObjectMapper om = null;
		
		// Get the string that represents the resource the user is requesting
		String resourceReq = request.getParameter(RESOURCE_REQUEST) ;
		LogUtil.logInfo("Requested Resource: " + resourceReq);
		
		// If this is a logout request, log the user out. 
		if (resourceReq.equals(LOGOUT)) {
			System.out.println("Logged Out ");
			request.getSession(false).invalidate();
			return;
		}
		
		// Get a connection. We're going to need it for pretty much all requests
		try (Connection con = DBConnectionUtil.getConnection()) {
			// Initialize object mapper. 
			om = new ObjectMapper();
			
			// If the user is requesting pending reimbursements, fetch, and return 
			// as JSON. 
			if (resourceReq.equals(PENDING)) {
				Employee emp = empDao.getEmployeeByUsername(username, con);
				
				// Get the pending requests for the specified employee
				List<Reimbursement> reimbursements = remDao.getPendingByRequester(emp, con);
				
				String remString = om.writeValueAsString(reimbursements);
				
				// Send the output and return. 
				pw.println(remString);
				return;
			}
		} catch (SQLException ex) {
			// If sql error, send a 500 code, and return. 
			response.sendError(500);
		}
	} // end of routeResource
	
} // end of class ResourceRequestHelper

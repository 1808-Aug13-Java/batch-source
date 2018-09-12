package com.revature.servlets.helpers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
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
import com.revature.models.Manager;
import com.revature.models.Reimbursement;
import com.revature.util.DBConnectionUtil;
import com.revature.util.LogUtil;

public class ResourceRequestHelper {
	
	
	private ResourceRequestHelper () {}
	
	/**
	 * This string represents the expected parameter of a resource request
	 */
	public static final String RESOURCE_REQUEST = "resourceRequest";
	
	
	private static final String LOGOUT = "logout";
	private static final String PENDING = "pending";
	private static final String RESOLVED = "resolved";
	private static final String PROFILE = "profile";
	private static final String NEW_REIMBURSEMENT = "newReim";
	
	// Manager Functions
	private static final String ALL_PENDING = "allPending"; 
	private static final String ALL_RESOLVED = "allResolved";
	private static final String ALL_EMPLOYEES = "allEmployees";
	private static final String EMPLOYEE_PENDING = "empPending";
	private static final String APPROVE_DENY = "approveDeny";
	
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
			LogUtil.logInfo("Logged Out ");
			request.getSession(false).invalidate();
			return;
		}
		
		// Get a connection. We're going to need it for pretty much all requests
		try (Connection con = DBConnectionUtil.getConnection()) {
			// Initialize object mapper. 
			om = new ObjectMapper();
			
			// Get the employee associated with the user. 
			Employee emp = empDao.getEmployeeByUsername(username, con);
			
			// If there isn't an employee associated with the user, invalidate
			// the sesion. 
			if (emp == null) {
				request.getSession(false).invalidate();
				response.sendError(400);
				return;
			}
			
			// If the user is requesting pending reimbursements, fetch & return 
			// as JSON. 
			if (resourceReq.equals(PENDING)) {
				// Get the pending requests for the specified employee
				List<Reimbursement> reimbursements = remDao.getPendingByRequester(emp, con);
				
				String remString = om.writeValueAsString(reimbursements);
				
				// Send the output and return. 
				pw.println(remString);
			}
			// If the user is requesting pending reimbursements, fetch & return 
			// as JSON. 
			else if (resourceReq.equals(ALL_PENDING)) {
				// Confirm that the employee is a manager. If not, invalidate
				// the session, as the employee shouldn't have tried to access
				// manager functions 
				if (manDao.tryCastManager(emp, con) == null) {
					request.getSession(false).invalidate();
					return;
				}
				
				// Get all the pending requests
				List<Reimbursement> reimbursements = remDao.getAllPending(con);
				
				String remString = om.writeValueAsString(reimbursements);
				
				// Send the output and return. 
				pw.println(remString);
			}
			// If the user is requesting pending reimbursement request for a 
			// specific employee, fetch and return as JSON
			else if (resourceReq.equals(EMPLOYEE_PENDING)) {
				// Confirm that the employee is a manager. If not, invalidate
				// the session, as the employee shouldn't have tried to access
				// manager functions 
				if (manDao.tryCastManager(emp, con) == null) {
					request.getSession(false).invalidate();
					return;
				}
				
				// If there are additional parameters, get them. 
				String empId = request.getParameter("id");
				if (empId != null) {
					// TODO better validation
					Employee otherEmp = empDao.getEmployeeById(Long.parseLong(empId), con);
					
					// Get all the pending requests for the employee
					List<Reimbursement> employees = remDao.getByRequester(otherEmp, con);
					
					String remString = om.writeValueAsString(employees);
					
					// Send the output and return. 
					pw.println(remString);
				}
				// Otherwise, there is a problem with this request. 
				else {
					LogUtil.logDebug("No Id Param");
					response.sendError(400);
				}
			}
			// If the user is requesting resolved reimbursements, fetch & return 
			// as JSON. 
			else if (resourceReq.equals(RESOLVED)) {
				// Get the resolved requests for the specified employee
				List<Reimbursement> reimbursements = remDao.getResolvedByRequester(emp, con);
				
				String remString = om.writeValueAsString(reimbursements);
				
				// Send the output. 
				pw.println(remString);
			}
			// If the user is requesting resolved reimbursements, fetch & return 
			// as JSON. 
			else if (resourceReq.equals(ALL_RESOLVED)) {
				// Confirm that the employee is a manager. If not, invalidate
				// the session, as the employee shouldn't have tried to access
				// manager functions 
				if (manDao.tryCastManager(emp, con) == null) {
					request.getSession(false).invalidate();
					return;
				}
				
				// Get all the resolved requests
				List<Reimbursement> reimbursements = remDao.getAllResolved(con);
				
				String remString = om.writeValueAsString(reimbursements);
				
				// Send the output and return. 
				pw.println(remString);
			}
			// If the user is requesting resolved reimbursements, fetch & return 
			// as JSON. 
			else if (resourceReq.equals(ALL_EMPLOYEES)) {
				// Confirm that the employee is a manager. If not, invalidate
				// the session, as the employee shouldn't have tried to access
				// manager functions 
				if (manDao.tryCastManager(emp, con) == null) {
					request.getSession(false).invalidate();
					return;
				}
				
				// Get all the employees
				List<Employee> employees = empDao.getAllEmployees(con);
				
				String remString = om.writeValueAsString(employees);
				
				// Send the output and return. 
				pw.println(remString);
			}
			// If the user is requesting resolved reimbursements, fetch & return 
			// as JSON. 
			else if (resourceReq.equals(PROFILE)) {
				// If there are additional parameters, get them, and update the 
				// employee. 
				String empName = request.getParameter("name");
				if (empName != null) {
					LogUtil.logInfo("Sending Update " + empName);
					emp.setName(empName);
					empDao.updateEmployee(emp, con);
				}
				
				// Remove the password hash before marshaling. 
				emp.setPasswordHash(null);
				
				String remString = om.writeValueAsString(emp);
				
				// Send the output. 
				pw.println(remString);
			}
			// If this is a new reimbursement request, get the parameters and 
			// insert it into the database. 
			else if (resourceReq.equals(NEW_REIMBURSEMENT)) {
				// If there are additional parameters, get them, and submit the 
				// reimbursement. 
				String amount = request.getParameter("amount");
				String description = request.getParameter("description");
				if (amount != null && description != null) {
					LogUtil.logInfo("New Reimbursement Request");
					
					// Create and populate a new Reimbursement
					Reimbursement reim = new Reimbursement();
					reim.setRequester(emp);
					reim.setDescription(description);
					reim.setStatus("Pending");
					reim.setSubmitDate(new Date());
					try {
						reim.setAmount(Double.parseDouble(amount));
					} catch (NumberFormatException ex2) {
						// If there is a problem parsing the number, return error
						response.sendError(400);
						return;
					}
					
					// Update the server with the new Reimbursement
					remDao.createReimRequest(reim, con);
					
					LogUtil.logInfo(reim);
				} else {
					// One of the additional parameters wasn't found, return error
					response.sendError(400);
				}
			}
			// Otherwise, if it is an approve or deny request, update the 
			// reimbursement table accordingly. 
			else if (resourceReq.equals(APPROVE_DENY)) {
				// Confirm that the employee is a manager. If not, invalidate
				// the session, as the employee shouldn't have tried to access
				// manager functions 
				Manager man = manDao.tryCastManager(emp, con);
				if (man == null) {
					request.getSession(false).invalidate();
					return;
				}
				
				// If there are additional parameters, get them. 
				String remId = request.getParameter("remId");
				String approve = request.getParameter("bool");
				if (remId != null && approve != null) {
					// TODO better validation
					Reimbursement rem = remDao.getById(Long.parseLong(remId), con);
					
					rem.setResolveDate(new Date());
					rem.setReason("");
					rem.setResolvedBy(man);
					switch(approve) {
					case "true": rem.setStatus("Approved"); break;
					case "false": rem.setStatus("Denied"); break;
					default: LogUtil.logDebug("Invalid boolean string: " + approve);
					}
					
					remDao.updateReimRequest(rem, con);
				}
				// Otherwise, there is a problem with this request. 
				else {
					LogUtil.logDebug("No Id Param");
					response.sendError(400);
				}
			}
			// Otherwise, log that we couldn't find a resource match. 
			else {
				LogUtil.logInfo("No Matching Resource Found: " + resourceReq);
			}
		} catch (SQLException ex) {
			// If sql error, send a 500 code, and return. 
			response.sendError(500);
			LogUtil.logDebug(ex);
		}
	} // end of routeResource
	
} // end of class ResourceRequestHelper

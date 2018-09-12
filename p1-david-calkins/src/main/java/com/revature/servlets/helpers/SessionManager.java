package com.revature.servlets.helpers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Employee;
import com.revature.util.DBConnectionUtil;
import com.revature.util.StringHasher;

public class SessionManager {
	
	// Private Empty Constructor to prevent instantiation. 
	private SessionManager() {}
	
	private static final int TIMEOUT_SECONDS = 7200;
	
	public static final String NO_MATCH_STRING = "no match";
	
	public static final String MATCH_STRING = "success";
	
	/** Provided an HttpServletRequest and an HttpServletResponse, attempts
	 * to create a session. Attempts to pull 'username' and 'password' 
	 * parameters from the request. It tests these against the values found 
	 * in the server. If a matching username and password are found, 
	 * creates the session, and redirects the client to the logged in page. 
	 * Otherwise, sends that the password is invalid. */
	public static void tryCreateSession(HttpServletRequest request, 
							HttpServletResponse response) throws IOException 
	{
		// Get the PrintWriter for the response
		PrintWriter pw = response.getWriter();
		
		EmployeeDao empDao = new EmployeeDaoImpl();
		
		// Get the parameters from the user. 
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		// If the request didn't have a recognizable username or password, 
		// send a 400 status code. 
		if (username == null || password == null) {
			response.sendError(400);
		}
		
		// Attempt to connect to the database. 
		try (Connection con = DBConnectionUtil.getConnection()) {
			Employee emp = empDao.getEmployeeByUsername(username, con);
			
			// If the returned value isn't null (which means the username 
			// matches), and the password hash matches, make a new session, 
			// and redirect the user. 
			if (emp != null 
				&& emp.getPasswordHash().equals(StringHasher.sha256Hash(password)))
			{
				request.getSession().setAttribute("username", username);
				request.getSession().setMaxInactiveInterval(TIMEOUT_SECONDS);
//				response.sendRedirect(request.getRequestURL().toString());
				pw.println(MATCH_STRING);
			}
			// Otherwise, there was no match. Send that there wasn't a match. 
			else {
				pw.println(NO_MATCH_STRING);
			}
			
		} catch (SQLException ex) {
			// If there is a problem connecting to the database, send 500 error
			ex.printStackTrace();
			response.sendError(500);
		}
	} // end of tryCreateSession
	
} // end of class SessionManager

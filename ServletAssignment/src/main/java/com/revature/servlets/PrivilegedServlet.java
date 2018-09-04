package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrivilegedServlet extends HttpServlet {
	
	/** Handles anyone trying to use a get request. */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// Don't let the Hax0rs know of its existence. 
		response.sendError(404, request.getRequestURI());
	}
	
	/** Handles login requests. */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// If the user has been validated
		if (request.getAttribute("validated") != null) {
			PrintWriter pw = response.getWriter();
			pw.println("<p>I've Successfully Hacked The Data!</p>");
		}
		// Don't let the Hax0rs know of its existence. 
		else {
			response.sendError(404, request.getRequestURI());
		}
	}
} // end of class PrivilegedServlet

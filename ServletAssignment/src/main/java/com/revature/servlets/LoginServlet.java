package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	/** Displays login page. */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("Received Get");
		// Send back the login page to the requester
		RequestDispatcher rd = request.getRequestDispatcher("Login.html");
		rd.forward(request, response);
	}
	
	/** Handles login requests. */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("Received Post");
		
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		
		// If the user is the root, I'm sure we can trust him with the data. 
		if (username != null && username.equals("root") && 
				password!=null && password.equals("")) 
		{
			RequestDispatcher rd = request.getRequestDispatcher("privileged");
			// The user has been validated
			request.setAttribute("validated", "");
			System.out.println("Forwarding Request");
			rd.forward(request, response);
		}
		// Otherwise, tell them off. 
		else {
			response.getWriter().println("<p> That is a bad login, and you "
					+ "should feel bad for having attempted it. </p>");
		}
	}
} // end of class LoginServlet

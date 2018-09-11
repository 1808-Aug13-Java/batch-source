package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.servlets.helpers.SessionManager;

public class MasterServlet extends HttpServlet {
	
	/**
	 * Default Serial Id
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		System.out.println("Get Request Recieved");
		
		// If the request already has a session, say so
		if (request.getSession(false) != null) {
			System.out.println("Praise the LORD!");
			System.out.println(request.getSession().getAttribute("username"));
		}
		try {
			System.out.println(request.getRequestURL().toString());
			// TODO: Implement https enforcement. 
			//If the request is not made using HTTPS, redirect to use HTTPS
//			if (!request.isSecure()) {
//				response.sendRedirect(request.getRequestURL().toString().replaceFirst("http", "https"));
//				return;
//			}
			
			RequestDispatcher rd = request.getRequestDispatcher("Views/Login.html");
			rd.forward(request, response);
		} catch(ServletException ex) {
			ex.printStackTrace();
			
			// Attempt to send a 500 error. 
			try {response.sendError(500);} catch (IOException e) {}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST Request Recieved");
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		
		
		// If there is no session for this request, check the username and 
		// password. If it is a good match, save a new session. 
		if (request.getSession(false) == null) {
			SessionManager.tryCreateSession(request, response);
			return;
		}
		else {
			System.out.println("Already Logged! " + request.getSession().getAttribute("username"));
		}
		
	}
	
} // end of class MasterServlet

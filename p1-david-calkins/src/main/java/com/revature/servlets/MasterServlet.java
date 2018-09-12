package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.servlets.helpers.HtmlManager;
import com.revature.servlets.helpers.ResourceRequestHelper;
import com.revature.servlets.helpers.SessionManager;

public class MasterServlet extends HttpServlet {
	
	/**
	 * Default Serial Id
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		System.out.println("Get Request Recieved");
		
		// Get and print the full URL + query args (if applicable)
		String queryArgs = request.getQueryString() == null ?
							"" : "?" + request.getQueryString();
		System.out.println(request.getRequestURL().toString() + queryArgs);
		
		
		try {
			// If the request already has a session, say so
			if (request.getSession(false) != null) {
				System.out.println("User: " + request.getSession().getAttribute("username"));
				
				// If the user is requesting a resource, send it to the resource
				// helper. 
				if (ResourceRequestHelper.isResourceRequest(request)) {
					System.out.println("Delegating to ResourceHelper");
					ResourceRequestHelper.routeResource(request, response);
				}
				// Otherwise, just navigate to the appropriate page for the user
				else {
					System.out.println("Delegating to HtmlHelper");
					HtmlManager.routePage(request, response);
				}
			}
			
			// Otherwise, Display the login page if not logged in. 
			else {
				// TODO: Implement https enforcement. 
				//If the request is not made using HTTPS, redirect to use HTTPS
//				if (!request.isSecure()) {
//					response.sendRedirect(request.getRequestURL().toString().replaceFirst("http", "https"));
//					return;
//				}
				
				RequestDispatcher rd = request.getRequestDispatcher("Views/Login.html");
				rd.forward(request, response);
			}
			
		} catch(ServletException ex) {
			ex.printStackTrace();
			
			// Attempt to send a 500 error. 
			try {response.sendError(500);} catch (IOException e) {}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST Request Recieved");
		
		// If a username has been provided for this request, check the username and 
		// password. If it is a good match, save a new session. 
		if (request.getParameter("username") != null) {
			System.out.println("username: " + request.getParameter("username"));
			System.out.println("pasword: " + request.getParameter("password"));
			SessionManager.tryCreateSession(request, response);
			return;
		}
		// Otherwise, if there is already a session, 
		else if (request.getSession(false) != null){
			System.out.println("Already Logged! " + request.getSession().getAttribute("username"));
			HtmlManager.routePage(request, response);
		}
		
	}
	
} // end of class MasterServlet

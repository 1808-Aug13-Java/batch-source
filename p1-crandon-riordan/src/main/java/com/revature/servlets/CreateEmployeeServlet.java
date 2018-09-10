package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.SessionHelper;

/**
 * Servlet implementation class CreateEmployeeServlet
 */
public class CreateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("employeeCreation.html").forward(request, response);
		SessionHelper sh = new SessionHelper();
		boolean foundUser = sh.checkValidUser(request);
		// ensure valid user
		if(!foundUser) {
			response.sendRedirect("login");
		} else {
			
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("posting");
		SessionHelper sh = new SessionHelper();
		boolean foundUser = sh.checkValidUser(request);
		
		if(!foundUser) {
			response.sendRedirect("login");
		}
		
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String managerId = request.getParameter("managerId");
		String isManager = request.getParameter("isManager");
	}

}

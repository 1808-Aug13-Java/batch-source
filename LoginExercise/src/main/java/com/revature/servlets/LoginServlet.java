package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Login.html");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);
		if (username.equals("admin") && password.equals("password")) {
			System.out.println("username and password match");
			RequestDispatcher rd = request.getRequestDispatcher("accessgranted");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("accessdenied");
			rd.forward(request, response);
		}
	}
}

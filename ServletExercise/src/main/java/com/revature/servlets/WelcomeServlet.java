package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Welcome get method");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("user: " + name + " password: " + password);
		PrintWriter pw = response.getWriter();
		if(name.equals("crandon") && password.equals("riordan")) {
			pw.write("Correct information!");
		} else {
			pw.write("Incorrect username or password");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Welcome post method");
		doGet(request, response);
	}

}

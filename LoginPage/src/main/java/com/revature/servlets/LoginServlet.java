package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get request handler method invoked successfully");
		PrintWriter pw = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("Login.html");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get request handler method invoked successfully");
		String uname = request.getParameter("uname");
		String psw = request.getParameter("psw");
		if(uname.equals("martin")&&psw.equals("password")) {
			RequestDispatcher rd = request.getRequestDispatcher("home.html");
			rd.forward(request, response);
		}else {
			PrintWriter pw = response.getWriter();
			pw.write("<p>WRONG.</p>");
		}
	}
}

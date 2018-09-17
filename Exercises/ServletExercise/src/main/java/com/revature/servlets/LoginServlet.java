package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
// import com.revature.models.Employee;

public class LoginServlet extends HttpServlet {
	/*
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("Service method was called");
		PrintWriter pw = response.getWriter(); // object we use to...
		pw.println("Hello World");
	}

	public void inti(ServletConfig config) throws ServletException {
		System.out.println("Init method was called");
	}
	
	public void destroy() {
		System.out.println("Destroy method was called");
	}*/
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get request handler method invoked successfully");
		PrintWriter pw = response.getWriter();
		pw.write("<p> Login Page </p>");
		RequestDispatcher rd = request.getRequestDispatcher("Login.html");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do post request handler method invoked successfully");
		String usn = request.getParameter("usn");
		String pwd = request.getParameter("pwd");

		System.out.println(usn + " " + pwd);
		
		String result = "<p> Login was ";
		if (usn.equals("sinhac")) {
			if (pwd.equals("youShallNotPass")) {
				result+="successful. </p>";
			} else {
				result+="unsuccessful, check your password. </p>";
			}
		} else {
			result+="unsuccessful. No such username exists. </p>";
		}
		request.setAttribute("answer", result);
		
		// RequestDispatcher rd = request.getRequestDispatcher("answer");
		// rd.forward(request, response);
		
		PrintWriter pw = response.getWriter();
		pw.write(result);
		// pw.write("<p> <a href=\"Calculator.html\">Calculate again</a> </p>");
	}
}

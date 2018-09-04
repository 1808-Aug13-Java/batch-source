package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get request handler method invoked successfully");
		PrintWriter pw = response.getWriter();
//		pw.write("<p> The calculator page is </p> <a href=\"Calculator.html\">here</a>");
		RequestDispatcher rd = request.getRequestDispatcher("LogIn.html");
		rd.forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
	
		System.out.println("do post request handler method invoked successfully");
		String n1 = request.getParameter("n1");
		String n2 = request.getParameter("n2");
	
		String realUser = "Jorge";
		String realPassword = "PASSWORD123";
		
		String result = "<p> Loggin ";
		String whereTo = "";

		if(n1.equals(realUser) || n2.equals(realPassword)) {
			result += "Successfull welcome" + realUser + "</p>";
			whereTo = "success";
		}
		else {
			result += "Unsuccessfull, Incorrect Username and/or Password <p>";
			whereTo = "failed";
		}
		
		
		
		request.setAttribute(whereTo, result);
		
		RequestDispatcher rd = request.getRequestDispatcher(whereTo);
		rd.forward(request, response);
	}
}

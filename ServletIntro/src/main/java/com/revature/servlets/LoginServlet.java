package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get request handler method invoked successfully");
		RequestDispatcher rd = request.getRequestDispatcher("Login.html");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testUsername = "kyla";
		String testPassword = "123";
		System.out.println("do post request handler method invoked successfully");
		String un = request.getParameter("un");
		String pw = request.getParameter("pw");
		
		System.out.println("un: " + un);
		System.out.println("pw: " + pw);
		System.out.println("Actual un: " + testUsername);
		System.out.println("Actual pw: " + testPassword);
		PrintWriter printwriter = response.getWriter();
		
		if (un.equals(testUsername)&& pw.equals(testPassword)) {
			printwriter.write("<p>You have successfully logged in</p>");
			printwriter.write("<p> <a href=\"Login.html\">Congratz wanna go back</a></p>");
			System.out.println("login successful");
		} else {
			printwriter.write("<p>Login has failed</p>");
			printwriter.write("<p> <a href=\"Login.html\">Attempt Again</a></p>");
			System.out.println("login failed");
		}
	}


}

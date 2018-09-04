package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FailServlet extends HttpServlet{

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Fail doGet");
		PrintWriter pw = response.getWriter();
		pw.write("<p>Incorrect username/password combination</p>");
		pw.write("<a href=\"Login.html\">Try again</a>");
	}
	
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Fail doPost");
		doGet(request, response);
	}
}

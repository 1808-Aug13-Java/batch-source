package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuccessServlet extends HttpServlet{

	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Success doGet");
		PrintWriter pw = response.getWriter();
		pw.write("<p>Successful login!</p>");
		pw.write("<a href=\"Login.html\">Log Out</a>");
	}
	
	@Override
	public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Success doPost");
		doGet(request, response);
	}
}

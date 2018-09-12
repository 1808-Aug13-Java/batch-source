package com.revature.servlets;

import com.revature.dao.*;
import com.revature.models.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet{
	private static final long serialVersionUID = 1L;
	SystemDAO dao = new SystemDAOImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String username = request.getParameter("user1");
		String pass = request.getParameter("pass1");
		String email = request.getParameter("email");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		dao.addname(fname, lname, username, pass);
		out.write("<body onload='myFunction()'><a href='#' onclick='myFunction()'>Redirecting...Click here to continue if nothing happens</a></body>\n<script>function myFunction(){\n");
		boolean success = dao.register(username, pass, email);
		if(success) {
			out.write("alert('Successfully registered.');");
		} else {
			out.write("alert('Registration failed or Username already taken.');");
		}
		out.write("window.location.href = 'Index.html';}</script>");
		
	}
}
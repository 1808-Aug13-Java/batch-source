package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("Login doGet");
		RequestDispatcher rd = request.getRequestDispatcher("Login.html");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("Login doPost");
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
//		System.out.println(uname + " " + pass);
		
		RequestDispatcher rd;
		if ((uname.equals("josh")) && (pass.equals("josh"))) {
			rd = request.getRequestDispatcher("success");
		} else {
			rd = request.getRequestDispatcher("fail");
		}
		
		rd.forward(request, response);
	}
}

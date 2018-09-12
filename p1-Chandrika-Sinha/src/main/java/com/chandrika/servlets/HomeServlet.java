package com.chandrika.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chandrika.util.User;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 2902623799834818962L;

	public HomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		if (!User.isNull() && !User.isManager()) {
			RequestDispatcher rq = request.getRequestDispatcher("Views/Employee/EmployeeHome.html");
			 rq.forward(request, response);
		} else {
			RequestDispatcher rq = request.getRequestDispatcher("Views/Login.html");
			rq.forward(request, response);
		}	  
	}
}

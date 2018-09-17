package com.chandrika.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chandrika.util.User;

public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6776335356848636745L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!User.isNull() && !User.isManager()) {
			User.setUserNull();
		} 
		RequestDispatcher reqD = request.getRequestDispatcher("Views/Login.html");
		reqD.forward(request, response);
	}
}

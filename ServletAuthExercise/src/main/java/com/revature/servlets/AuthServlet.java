package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("Authenticate.html");
		rd.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);

		System.out.println(username);
		System.out.println(password);
		if (username.equals("username") && password.equals(password)) {
			String result = "<p>";
			result += " " + username + ", you are now logged in!" + "</p>";
			request.setAttribute("username", result);
			RequestDispatcher rd = request.getRequestDispatcher("success");
			rd.forward(request, response);
		} else {
			String result = "<p>";
			request.setAttribute("username", result);
			result += "</p>";
			RequestDispatcher rd = request.getRequestDispatcher("failure");
			rd.forward(request, response);
		}

	}

}

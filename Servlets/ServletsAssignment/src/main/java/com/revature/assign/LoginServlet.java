package com.revature.assign;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Do get request handler method invoked successfully");
		PrintWriter pw = response.getWriter();
		RequestDispatcher rd = request.getRequestDispatcher("Login.html");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("do post request handler method invoked succesfully");
		String n1 = request.getParameter("in1");
		String n2 = request.getParameter("in2");
		System.out.println(n1);
		System.out.println(n2);
		String name = "John";
		String pass = "Snow";
		System.out.println(name == n1);
		System.out.println(pass == n2);
		if (name.equals(n1) && pass.equals(n2)) {

			String result = "login Success!";

			request.setAttribute("correct", result);
			RequestDispatcher rd = request.getRequestDispatcher("correct");
			rd.forward(request, response);
		}

		else {
			String failure = "Not today son";
			request.setAttribute("wrong", failure);
			RequestDispatcher rd = request.getRequestDispatcher("wrong");
			rd.forward(request, response);
		}

	}

}

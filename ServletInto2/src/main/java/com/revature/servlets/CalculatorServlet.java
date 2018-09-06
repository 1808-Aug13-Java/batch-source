package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Do get request handler method invoked successfully");
//		PrintWriter pw = response.getWriter();
//		pw.write("<p> The calculator page is <a href=\"Calculator.html\">here</a></p>");
		RequestDispatcher rd = request.getRequestDispatcher("Calculator.html");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Do post request handler method invoked successfully");
		String n1 = request.getParameter("n1");
		String n2 = request.getParameter("n2");
		String operation = request.getParameter("operation");
		String result = "<p>" + n1 + " " + operation + " " + n2 + " = " ;
		Double first = Double.parseDouble(n1);
		Double second = Double.parseDouble(n2);
		switch(operation) {
			case "add":
				result += first + second;
				break;
			case "subtract":
				result += first - second;
				break;
			case "divide":
				result += first / second;
				break;
			case "multiply":
				result += first * second;
				break;
			default:
				result = "Invalid operation";
				break;
		}
		result += "</p>";
//		PrintWriter pw = response.getWriter();
//		pw.write(result);
//		pw.write("<a href=\"Calculator.html\"/>Calculate again</p>");
		request.setAttribute("answer", result);
		RequestDispatcher rd = request.getRequestDispatcher("answer");
		rd.forward(request, response);
	}
}

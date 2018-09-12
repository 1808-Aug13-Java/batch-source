package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Do get request handler method invoked successfully");
		PrintWriter pw = response.getWriter();
//		pw.write("<p> The calculator page is </p> <a href=\"Calculator.html\" >here</a>");
		RequestDispatcher rd = request.getRequestDispatcher("Calculator.html");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("do post request handler method invoked succesfully");
		String n1 = request.getParameter("n1");
		String n2 = request.getParameter("n2");
		String operation = request.getParameter("operation");
//		System.out.println(n1+" "+operation+" "+ n2);
		String result = "The answer is: ";
		Double first = Double.parseDouble(n1);
		Double second = Double.parseDouble(n2);
		switch (operation) {
		case "add": {
			result = "" + (first + second);
			break;
		}
		case "subtract": {
			result = "" + (first - second);
			break;
		}
		case "multiply": {
			result = "" + (first * second);
			break;
		}
		case "divide": {
			result = "" + (first / second);
			break;
		}
		default:
			result = "invalid operation";
		}
//		PrintWriter pw=response.getWriter();
//		pw.write(result);
		request.setAttribute("answer", result);
		RequestDispatcher rd = request.getRequestDispatcher("answer");
		rd.include(request,response);
//		rd.forward(request, response);

	}

}
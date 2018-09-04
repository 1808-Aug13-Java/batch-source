package com.revature.servlets;

import java.io.IOException;  
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
public class WelcomeServlet extends HttpServlet {  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get request handler method invoked successfully");
		PrintWriter pw = response.getWriter();
//		pw.write("<p> The calculator page is </p> <a href=\"Calculator.html\">here</a>");
		RequestDispatcher rd = request.getRequestDispatcher("text/html");
		rd.forward(request, response);
	}
	
public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
          
    String n=request.getParameter("username");  
    out.print("Welcome "+n);  
          
    out.close();  
    }  
  
}  

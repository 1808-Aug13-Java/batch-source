package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.SystemDAO;
import com.revature.dao.SystemDAOImpl;

public class Manage extends HttpServlet{
	private static final long serialVersionUID = 1L;
	SystemDAO dao = new SystemDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String RID = request.getParameter("rid");
		String UID = request.getParameter("approver");
		int status = 1;
		if (request.getParameter("actiontype").equals("true")) {
			status= 2;
		} else {
			status = 3;
		}
		boolean test = dao.statuschange(RID, UID, status);
		response.setContentType("text/html");
		out.write("<body onload='myFunction()'><a href='#' onclick='myFunction()'>Redirecting...Click here to continue if nothing happens</a></body>\n<script>function myFunction(){\n");
		if(test) {
			out.write("alert('Success');");
		}
		else {
			out.write("alert('Failed');");
		}
		out.write("window.location.href = 'Employee.html';}</script>");
	}
}
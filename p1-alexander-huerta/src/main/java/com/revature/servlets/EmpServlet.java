package com.revature.servlets;

import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();

    public EmpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.info("in employehome servlet: " + request.getParameter("userName") );
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    out.println("<p hidden id=\"username\">" + request.getParameter("userName") +"</p>");
	    request.getRequestDispatcher("views/employees.html").include(request, response); 
	    out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
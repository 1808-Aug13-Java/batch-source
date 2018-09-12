package com.revature.servlets;


import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.revature.controller.EmployeeCon;
import com.revature.controller.ServiceCon;

public class GetEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();
       
    public GetEmpServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.info("in getemployee: " + request.getParameter("username"));
		PrintWriter pw = response.getWriter();
		pw.print(EmployeeCon.getEmployeeByUserName(request.getParameter("username"), response));
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
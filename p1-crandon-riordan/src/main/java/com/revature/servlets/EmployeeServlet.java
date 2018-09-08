package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmpDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.util.SessionHelper;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String employeeId = request.getParameter("employeeId");
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		EmployeeDao ed = new EmpDaoImpl();
		List<Employee> employees = new ArrayList<Employee>();
		SessionHelper sh = new SessionHelper();
		boolean foundUser = sh.checkValidUser(request);
		 // ensure proper access to employee endpoint
		if(!foundUser) {
			response.sendRedirect("login");
		}
		if(employeeId != null) {
			
		} else {
			employees = ed.getAllEmployees();
			String employeesString = om.writeValueAsString(employees);
			employeesString = "{\"employees\": " + employeesString + "}";
			pw.write(employeesString);
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

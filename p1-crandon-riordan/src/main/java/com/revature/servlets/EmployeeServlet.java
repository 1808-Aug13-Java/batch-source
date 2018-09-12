package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmpDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.util.Hasher;
import com.revature.util.SessionHelper;
import com.revature.util.Validator;

/**
 * Servlet implementation class EmployeeServlet
 * 
 * SERVLET : EMPLOYEE API ENDPOINT TO GET EMPLOYEE DATA
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 ENDPOINT FOR EMPLOYEE DATA
		
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
			// handle employeeId param
			Employee employee = new Employee();
			employee = ed.getEmployeeById(Integer.parseInt(employeeId));
			if(employee != null) {
				String employeeString = om.writeValueAsString(employee);
				employeeString = "{\"employee\": " + employeeString + "}";
				pw.write(employeeString);
			} else {
				pw.write("null");
			}
		} else {
			// print all if no employeeId param in get request
			employees = ed.getAllEmployees();
			String employeesString = om.writeValueAsString(employees);
			employeesString = "{\"employees\": " + employeesString + "}";
			pw.write(employeesString);
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the session associated with account
		// then create an Employee obj to update w/
		SessionHelper sh = new SessionHelper();
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("id");
		
		// get params (password comes later for some more logic)
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Hasher hasher = new Hasher();
		EmployeeDao ed = new EmpDaoImpl();
		// ensure proper user is making req
		if(!sh.checkValidUser(request)) {
			response.sendRedirect("login");
		} else {
			if(id != null) {
				Integer parsedId = id;
				Employee employee = ed.getEmployeeById(parsedId);
				// validating input
				if(Validator.validEmail(email)) {
					employee.setEmail(email);
				} else {
					response.sendRedirect("employeeProfile");
				}
				if(Validator.validUsername(username)) {
					employee.setUsername(username);
				} else {
					response.sendRedirect("employeeProfile");
				}
				if(name.length() > 5) {
					employee.setName(name);
				} else {
					response.sendRedirect("employeeProfile");
				}
				if(Validator.validPassword(password)) {
					employee.setPassword(hasher.getHashPassword(password));
				} else {
					// if the password wasnt enter, just go ahead and keep same
					// TODO
				}
				
				ed.updateEmployee(employee);
				
				
				
				
				System.out.println("new emp " + employee);
			}
		}
		
	}

}

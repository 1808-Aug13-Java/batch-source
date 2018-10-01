package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.revature.dao.EmpDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.util.Hasher;
import com.revature.util.SessionHelper;
import com.revature.util.Validator;

/**
 * Servlet implementation class CreateEmployeeServlet
 */
public class CreateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(CreateEmployeeServlet.class);

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SessionHelper sh = new SessionHelper();
		boolean foundUser = sh.checkValidUser(request);
		// ensure valid user
		if(!foundUser) {
			response.sendRedirect("login");
		} else {
			request.getRequestDispatcher("employeeCreation.html").forward(request, response);
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SessionHelper sh = new SessionHelper();
		// hashing pw
		Hasher hasher = new Hasher();
		boolean foundUser = sh.checkValidUser(request);
		
		if(!foundUser) {
			response.sendRedirect("login");
		}
		
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = hasher.getHashPassword(request.getParameter("password"));
		String managerId = request.getParameter("managerId");
		String isManager = request.getParameter("isManager");
		
		Employee emp = new Employee();
		EmployeeDao ed = new EmpDaoImpl();
		// validating input
		if(Validator.validEmail(email) && password.length() > 7 && username.length() > 4) {
			emp.setName(name);
			emp.setUsername(username);
			emp.setEmail(email);
			emp.setPassword(password);
			try {
				// checking for correct input further
				
				emp.setIsManager(Integer.parseInt(isManager));
				if(emp.getIsManager() == 0) {
					emp.setManagerId(Integer.parseInt(managerId));
					ed.createEmployee(emp);
				} else {
					// handle manager creation
					ed.createManager(emp);
				}
				ed.createEmployee(emp);
			} catch (Exception e) {
				logger.info("Parsing error for employee creation");
				response.sendRedirect("createEmployee");
			}
			
		} else {
			logger.info("Invalid email or password");
		}
	}

}

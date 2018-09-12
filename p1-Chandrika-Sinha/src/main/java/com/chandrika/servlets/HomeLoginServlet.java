package com.chandrika.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chandrika.dao.EmployeeDao;
import com.chandrika.dao.EmployeeDaoImpl;
import com.chandrika.dao.ManagerDao;
import com.chandrika.dao.ManagerDaoImpl;
import com.chandrika.model.Employee;
import com.chandrika.model.Manager;
import com.chandrika.util.User;

public class HomeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = -224765235287518367L;

	public HomeLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rq = null;
		if (request.getParameter("btn").equals("I'm a Manager")) {
			ManagerDao md = new ManagerDaoImpl();
			String username = request.getParameter("username");
			Manager manager = md.getManagerByUsername(username);
			String password = request.getParameter("password");
			if (manager.login(password)) {
				User.setUser(manager.getId(), true);
			} else {
			}
		} else if (request.getParameter("btn").equals("I'm an Employee")) {
			EmployeeDao ed = new EmployeeDaoImpl();
			String username = request.getParameter("username");
			Employee employee = ed.getEmployeeByUsername(username);
			String password = request.getParameter("password");
			if (employee.login(password)) {
				User.setUser(employee.getId(), false);
				rq = request.getRequestDispatcher("Views/Employee/EmployeeHome.html");
			} else {
				rq = request.getRequestDispatcher("Views/Login.html");
			}
		}
		rq.forward(request, response);
	}
}
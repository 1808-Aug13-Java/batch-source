package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.model.Department;
import com.revature.model.Employee;
import com.revature.model.Location;

/**
 * Servlet implementation class NewEmployeeServlet
 */
public class NewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Views/CreateEmployee.html").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		int locationId = Integer.parseInt(request.getParameter("location"));
		int departmentId = Integer.parseInt(request.getParameter("department"));
		
		Employee e = new Employee();
		e.setName(name);
		Location l = new Location();
		l.setId(locationId);
		e.setLocation(l);
		Department d = new Department();
		d.setId(departmentId);
		e.setDepartment(d);
		
		System.out.println(e);
		EmployeeDao ed = new EmployeeDaoImpl();
		int numCreated = ed.createEmployee(e);
		System.out.println(numCreated);
		
		if (numCreated==1) {
			response.sendRedirect("success");
		} else {
			response.sendRedirect("error");
		}
	}

}

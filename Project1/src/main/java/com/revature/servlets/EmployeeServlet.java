package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ZukemployeeDao;
import com.revature.dao.ZukemployeeDaoImpl;
import com.revature.model.Zukemployee;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		//System.out.println("we got a param, value: "+idStr);
		
		ZukemployeeDao ed = new ZukemployeeDaoImpl();
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		
		//if we get a parameter we want to display a single employee
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			Zukemployee e = ed.getEmployeeById(id);
			//if we don't get anything from the database our id will be 0
			if(e.getId()==0) {
				pw.print("invalid employee id");
			//if we are returned an employee from the database we want to display it in json format
			} else {
				String employeeString = om.writeValueAsString(e);
				pw.write(employeeString);
			}
		//if we do not get a valid parameter, we want to display all employees
		} else {
			List<Zukemployee> employees = ed.getEmployees();
			String employeeString = om.writeValueAsString(employees);
			employeeString = "{\"employees\":"+employeeString+"}";
			pw.print(employeeString);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

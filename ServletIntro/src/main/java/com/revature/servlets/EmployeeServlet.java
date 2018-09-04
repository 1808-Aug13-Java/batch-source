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
import com.revature.models.Employee;

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
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> allEmployees = new ArrayList<>();
		allEmployees.add(new Employee(5, "Scarlett Knowles", "sgknowles@gmail.com"));
		allEmployees.add(new Employee(12, "Jerry Kirkland", "jkirk@gmail.com"));
		allEmployees.add(new Employee(52, "Lola Rothenburg", "lola.rothenburg@gmail.com"));
		allEmployees.add(new Employee(59, "John Johnson", "jjson@gmail.com"));

		
		ObjectMapper om = new ObjectMapper();
		String employeeString = om.writeValueAsString(allEmployees);
		employeeString = "{\"employees\":" + employeeString + "}";
		PrintWriter pw = response.getWriter();
		pw.print(employeeString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.revature.models;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Employee;

public class EmployeeServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Employee> allEmployees = new ArrayList<>();
		allEmployees.add(new Employee(34,"Sally Jones", "shones@gmail.com"));
		allEmployees.add(new Employee(56, "Kermit McNealy", "kmcneal@gmail.com"));
		allEmployees.add(new Employee(31, "Kylo Ben","theforce@gmail.com"));
		
		ObjectMapper om = new ObjectMapper();
		String employeeString = om.writeValueAsString(allEmplyee);
		employeeString = "{\"employees\":"+employeeString+"}";
	}
}

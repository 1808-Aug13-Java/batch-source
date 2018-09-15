package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Employee;

public class EmployeeController {
	
	private static Logger log = Logger.getRootLogger();

	public EmployeeController() {
		super();
	}
	
	public static String getEmployees() {
		
		EmployeeDao edi = new EmployeeDaoImpl();
		ObjectMapper om = new ObjectMapper();
		
		List<Employee> employee = edi.getEmployees();
		String employeeString = null;
		
		try {
			employeeString = om.writeValueAsString(employee);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		
		return "{\"employees\":" + employeeString + "}";
	}
	
	public static String getEmployeeByUsername(String username) {
		EmployeeDao edi = new EmployeeDaoImpl();
		ObjectMapper om = new ObjectMapper();
		
		Employee e = edi.getEmployeeByUsername(username);
		
		String empString = null;
		
		try {
			empString = om.writeValueAsString(e);
		} catch (JsonProcessingException e1) {
			log.error(e1.getMessage());
		}
		
		return empString;
	}
	
	public static int updateEmployee(HttpServletRequest request, String username) {
		int employeesUpdated = 0;
		EmployeeDao edi = new EmployeeDaoImpl();
		Employee e = new Employee();
		
		Employee oe = edi.getEmployeeByUsername(username);
		
		
		e.setFname(request.getParameter("fname").toString());
		e.setLname(request.getParameter("lname").toString());
		e.setId(oe.getId());
		e.setUsername(oe.getUsername());
		e.setPwd(request.getParameter("password").toString());
		e.setManagerId(oe.getManagerId());
		
		edi.updateEmployee(e);
		employeesUpdated = 1;
		
		return employeesUpdated;
		
	}

}

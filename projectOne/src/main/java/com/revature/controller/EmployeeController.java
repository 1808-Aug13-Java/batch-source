package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.doa.EmployeeDAO;
import com.revature.doa.EmployeeDoaImp;
import com.revature.model.Employee;

public class EmployeeController {

	private static Logger log = Logger.getRootLogger();
	
	private EmployeeController() {
		super();
	}
	
	public static String getEmployees() {

		EmployeeDAO ed = new EmployeeDoaImp();
		ObjectMapper om = new ObjectMapper();

		List<Employee> employees = ed.getEmployees();
		String employeeString = null;
		try {
			employeeString = om.writeValueAsString(employees);
		} catch (JsonProcessingException a) {
			log.error(a);
		}
		return "{\"employees\":" + employeeString + "}";
	}
	
	public static String getEmployeeByUserName(String userName) {

		EmployeeDAO ed = new EmployeeDoaImp();
		ObjectMapper om = new ObjectMapper();

		Employee employee = ed.getEmployeeByUserName(userName);
		
		String empStr = null;
		try {
			empStr = om.writeValueAsString(employee);
		} catch (JsonProcessingException a) {
			log.info(a);
		}
		return empStr;
		
	}
	
	public static void updateProfile(HttpServletRequest request, String username) {
		EmployeeDAO ed = new EmployeeDoaImp();
		Employee emp = new Employee();
		
		Employee oe = ed.getEmployeeByUserName(username);
		emp.setEmpId(oe.getEmpId());
		emp.setfName(request.getParameter("fName"));
		log.info("in update profile: " + request.getParameter("fName"));
		emp.setlName(request.getParameter("lName"));
		log.info("in update profile: " + request.getParameter("lName"));
		emp.setPswrd(request.getParameter("newPassword"));
		log.info("in update profile: " + request.getParameter("newPassword"));
		
		ed.updateEmployee(emp);
		
	}
}

package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.models.Employee;

public class EmployeeCon {

	private static Logger log = Logger.getRootLogger();
	
	private EmployeeCon() {
		super();
	}
	
	public static String getEmployees() {

		EmployeeDao ed = new EmployeeDaoImp();
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
	
	public static String getEmployeeByUserName(String userName, HttpServletResponse response) {

		EmployeeDao ed = new EmployeeDaoImp();
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
}
package com.revature.controller;

import java.util.List;

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
}

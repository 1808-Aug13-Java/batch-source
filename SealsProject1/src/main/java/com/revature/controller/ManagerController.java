package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.models.Employee;

public class ManagerController {

	
	public ManagerController() {
		super();
	}

	private static Logger log = Logger.getRootLogger();
	
	public static String getAllEmployees() {
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

}

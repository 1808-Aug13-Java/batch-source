package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.doa.EmployeeDAO;
import com.revature.doa.EmployeeDoaImp;
import com.revature.doa.ManagerDAO;
import com.revature.doa.ManagerDaoImpl;
import com.revature.doa.ReimbursmentDAO;
import com.revature.doa.ReimbursmentDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Manager;
import com.revature.model.Reimbursment;

public class ManagerController {
	
	private static Logger log = Logger.getRootLogger();

	private ManagerController() {
		super();
	}
	
	public static String getEmployeesManId(String userName, HttpServletResponse response) {

		ManagerDAO mand = new ManagerDaoImpl();
		EmployeeDAO ed = new EmployeeDoaImp();
		ObjectMapper om = new ObjectMapper();

		Manager manager = mand.getManagerByUserName(userName);
		
		List<Employee> employees = ed.getEmployeesByManId(manager.getManId());
		String employeeString = null;
		try {
			employeeString = om.writeValueAsString(employees);
		} catch (JsonProcessingException a) {
			log.error(a);
		}
		return "{\"employees\":" + employeeString + "}";
	}
}

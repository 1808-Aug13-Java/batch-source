package com.chandrika.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chandrika.intercom.ReimbursementClient;
import com.chandrika.models.Employee;
import com.chandrika.models.Reimbursement;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private Logger logger = Logger.getLogger(EmployeeController.class.getName());
	private List<Employee> employees = new ArrayList<>();

	@Autowired
	ReimbursementClient reimbursementClient;
	
	public EmployeeController() {
		employees.add(new Employee(1, "Poison Ivy", null));
		employees.add(new Employee(2, "Harley Quinn", null));
		employees.add(new Employee(3, "Batman", null));
		employees.add(new Employee(4, "Joker", null));
	}
	
	@GetMapping
	public List<Employee> getAllEmployees() {
		logger.info("EmployeeController.findAllEmployees");
		return employees;
	}
	
	@GetMapping(value="/{id}")
	public Employee getEmployeeById(@PathVariable("id") int employeeId) {
		logger.info("EmployeeController.getEmployeeById()");
		Employee employee = employees.stream().filter(emp -> emp.getEmployeeId() == employeeId).findFirst().get();
		List<Reimbursement> reimbursements = reimbursementClient.getAllReimbursements(employeeId);
		employee.setReimbursements(reimbursements);
		return employee;
	}
}

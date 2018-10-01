package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private Logger logger = Logger.getLogger(Employee.class);
	
	private List<Employee> employees = new ArrayList<>();
	
	public EmployeeController() {
		employees.add(new Employee(1, "josh", null));
		employees.add(new Employee(2, "john", null));
		employees.add(new Employee(3, "joseph", null));
		
	}
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		logger.info("EmployeeController.getAllEmployees");
		return employees;
	}
	
	@GetMapping(value="/{id}")
	public Employee getEmployeeById(@PathVariable("id") int employeeId) {
		logger.info("EmployeeController.getEmployeeById");
		Employee employee = employees.stream().filter(emp -> emp.getEmployeeId() == employeeId).findFirst().get();
		
		return employee;
	}
}

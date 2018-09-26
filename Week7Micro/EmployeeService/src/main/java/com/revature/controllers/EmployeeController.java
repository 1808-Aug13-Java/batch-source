package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.intercom.ReimbursmentClient;
import com.revature.models.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private Logger logger = Logger.getLogger(EmployeeController.class.getName());
	
	@Autowired
	ReimbursmentClient reimbursmentClient;
	
	private List<Employee> employees = new ArrayList<>();
	
	public EmployeeController() {
		employees.add(new Employee(1, "Crandon Riordan", "criordna@gmail.com", null));
		employees.add(new Employee(2, "Jennifer Lawrence", "jlaw@gmail.com", null));
		employees.add(new Employee(3, "Ryan Gosling", "rgosling@gmail.com", null));
		employees.add(new Employee(4, "Shawn Mendes", "smendes@gmail.com", null));
	}
	
	@GetMapping
	public List<Employee> getAllEmployees() {
		return employees;
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id) {
		
		Employee employee = employees.stream().filter(emp -> emp.getEmployeeId() == id).findFirst().get();
		logger.info("getEmployeeById called");
		employee.setReimbursments(reimbursmentClient.getReimbursmentsByCustomerId(id));
		
		return employee;
	}
}

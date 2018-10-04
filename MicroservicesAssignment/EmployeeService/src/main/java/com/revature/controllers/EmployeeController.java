package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.intercom.ReimbursementClient;
import com.revature.models.Employee;
import com.revature.models.Reimbursement;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	private Logger logger = Logger.getLogger(EmployeeController.class.getName());
	private List<Employee> employees = new ArrayList<>();	
	
	@Autowired
	ReimbursementClient reimbursementClient;
	
	public EmployeeController() {
		employees.add(new Employee(1, "Will Fenrer", "Manager"));
		employees.add(new Employee(2, "Bam Margera", "Employee"));
		employees.add(new Employee(3, "Wimbles", "Employee"));
		employees.add(new Employee(4, "Maxwell Jacob Friedman", "Manager"));	
	}
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		logger.info("EmployeeController.findAllEmployees(");
		return employees;
	}
	
	@GetMapping(value="/{employeeID}")
	public Employee getEmployeeById(@PathVariable("employeeID") int employeeID) {
		logger.info("EmployeeController.getEmployeeById");
		Employee employee = employees.stream().filter(emp -> emp.getEmployeeID() == employeeID).findFirst().get();
		List<Reimbursement> reimbursements = reimbursementClient.getReimbursements(employeeID);
		employee.setReimbursements(reimbursements);
		return employee;
	}
}

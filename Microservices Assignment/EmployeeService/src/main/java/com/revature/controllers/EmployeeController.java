package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
	
	private List<Employee> employees = new ArrayList();
	
	@Autowired
	ReimbursementClient reimbursementClient;
	
	
	public EmployeeController() {
		employees.add(new Employee(1, "Jim Brown", "jbrown@gmail.com", null));
		employees.add(new Employee(2, "Sally Jones", "sjones@me.com", null));
		employees.add(new Employee(3, "Bob Smith", "bsmith@hotmail.com", null));
		employees.add(new Employee(4, "Mary Stevens", "mstevens@aol.com", null));
	}
	
	@GetMapping
    public List<Employee> getAllEmployees() {
        logger.info("EmployeeController.getAllEmployees");
        return employees;
    }
	
	@GetMapping(value="/{employeeId}")
	public Employee getEmployeeById(@PathVariable("employeeId") int employeeId) {
		logger.info("EmployeeController.getEmployeeById");
		Employee employee = employees.stream().filter(emp -> emp.getEmployeeId() == employeeId).findFirst().get();
		List<Reimbursement> reimbursements = reimbursementClient.getAllReimbursements(employeeId);
		employee.setReimbursements(reimbursements);
		return employee;
	}
}

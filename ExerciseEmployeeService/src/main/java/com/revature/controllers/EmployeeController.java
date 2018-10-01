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

	private Logger log = Logger.getLogger(EmployeeController.class.getName());
	private List<Employee> employees = new ArrayList();
	
	@Autowired
	ReimbursementClient rc;
	
	public EmployeeController() {
		
	}
	
	@GetMapping
	public List<Employee> getAllEmployees() {
		log.info("Finding All Employees");
		return employees;
	}
	
	@GetMapping(value="/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id) {
		log.info("Finding Employee by ID");
		Employee emp = employees.stream().filter(empla -> empla.getEmployeeId() == id)
				.findFirst().get();
		List<Reimbursement> reimbursements = rc.getReimbursements(id);
		emp.setReimbursements(reimbursements);
		return emp;
	}
}

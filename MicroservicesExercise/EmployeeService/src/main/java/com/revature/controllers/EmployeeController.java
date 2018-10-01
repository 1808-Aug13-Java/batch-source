package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.intercom.ReimbursementsClient;
import com.revature.models.Employees;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private Logger logger = Logger.getLogger(EmployeeController.class.getName());
	
	@Autowired
	ReimbursementsClient reimbursementsClient;
	
	private List<Employees> employees = new ArrayList<>();
	
	public EmployeeController() {
		employees.add(new Employees(1, "Johnny Franck", "jfranck@bilmuri.org", null));
		employees.add(new Employees(2, "Tilian Pearson", "tilian@gmail.com", null));
		employees.add(new Employees(3, "Jon Mess", "drmess@aol.com", null));
	}
	
	@GetMapping
	public List<Employees> getAllEmployees() {
		return employees;
	}
	
	@GetMapping("/{id}")
	public Employees getEmployeeById(@PathVariable("id") int id) {
		Employees employee = employees.stream().filter(emp -> emp.getEmployeeId() == id).findFirst().get();
		logger.info("getEmployeeById called");
		employee.setReimbursements(reimbursementsClient.getReimbursementsByCustomerId(id));
		
		return employee;
	}
}

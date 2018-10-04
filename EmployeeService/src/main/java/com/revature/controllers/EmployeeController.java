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
		employees.add(new Employee(1, "Lawrence", "ofArabia@aol.com", null));
		employees.add(new Employee(2, "Killgore Trout", "kurt@aol.com", null));
		employees.add(new Employee(3, "Molly", "ring@wall.com", null));
		employees.add(new Employee(4, "Galactus", "devour@aol.com", null));
	}	
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		logger.info("EmployeeController.findAllEmployees(");
		return employees;
	}
	
	
	@GetMapping(value="/{id}")
	public Employee getEmployeeById(@PathVariable("id") int empId){
		logger.info("EmployeeController.getEmployeeById");
		Employee employee = employees.stream().filter(emp -> emp.getEmpId() == empId).findFirst().get();
		List<Reimbursement> reimbursements = reimbursementClient.getReimbursements(empId);
		employee.setReimbursements(reimbursements);
		return employee;
	}


}

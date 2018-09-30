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
		employees.add(new Employee(1, "Billy Pilgrim", "billp@gmail.com", null));
		employees.add(new Employee(2, "Jennifer Lawrence", "jenl@gmail.com", null));
		employees.add(new Employee(3, "Bart Simpson", "bsimps@gmail.com", null));
		employees.add(new Employee(4, "Kurt Vonnegut", "kvon@gmail.com", null));
	}
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		logger.info("EmployeeController.findAllEmployees()");
		return employees;
	}
	
	@GetMapping(value="/{id}")
	public Employee getEmployeeById(@PathVariable("id") int employeeId) {
		logger.info("EmployeeController.getEmployeeById");
		Employee employee = employees.stream().filter(cust -> cust.getEmployeeId() == employeeId).findFirst().get();
		List<Reimbursement> reimbursements = reimbursementClient.getReimbursements(employeeId);
		employee.setReimbursements(reimbursements);
		return employee;
	}

}

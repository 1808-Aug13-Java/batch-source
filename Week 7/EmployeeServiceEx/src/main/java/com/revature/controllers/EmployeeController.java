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
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	ReimbursementClient reimClient;
	
	private Logger logger= Logger.getLogger(EmployeeController.class.getName());

	private List <Employee> employees = new ArrayList<>();
	
	public EmployeeController () {
		this.employees.add(new Employee(1L, "Tom", null));
		this.employees.add(new Employee(2L, "Juan", null));
		this.employees.add(new Employee(3L, "Marcus", null));
		this.employees.add(new Employee(4L, "Susan", null));
		this.employees.add(new Employee(5L, "Annie", null));
		this.employees.add(new Employee(6L, "Sofia", null));		
	}
	
@GetMapping
public List<Employee> getAllEmployees(){
	logger.info("get all employees called ");
	return this.employees;
}

@GetMapping (value="/id")
public Employee getEmployeeById(@PathVariable("id") long id) {
	//inset feign client implementation here
	Employee emp =this.employees.stream().filter(em -> em.getEmployeeId()==id ).findFirst().get(); 
	List<Reimbursement> empReims= reimClient.getReimbursementByCustomerId(id);
	emp.setReims(empReims);
	return emp;
}



}

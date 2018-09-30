package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	/** A list of employee objects. Used to hold employees instead of 
	 * a database. */
	List<Employee> employees = new ArrayList<>();
	
	
	
	public EmployeeController() {
		
	} // end of constructor EmployeeController
	
	
	
	/** Returns a list of all employees. */
	@GetMapping() 
	public List<Employee> getAllEmployees() {
		//TODO: Implement the getting of the reimbursements. 
		return employees;
	} // end of getAllEmployees
	
	
	
	/** Returns the employee with the specified ID, or null if there is no such 
	 * employee with that ID. */
	@GetMapping("/{id}") 
	public Employee getEmployeeById(@PathVariable long empId) {
		// Search the list of employees for the id that matches. 
		Employee emp = null;
		for (Employee e : employees) {
			if (e.getId() == empId) {
				emp = e;
				break;
			}
		} // end for
		
		//TODO: Implement the getting of the reimbursements. 
		return emp;
	} // end of getAllEmployees
	
	
	
	
	
} // end of EmployeeController

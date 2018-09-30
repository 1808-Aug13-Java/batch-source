package com.revature.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.intercom.ReimbursementClient;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	/** A list of employee objects. Used to hold employees instead of 
	 * a database. */
	List<Employee> employees = new ArrayList<>();
	
	
	@Autowired
	private ReimbursementClient remClient;
	
	
	public EmployeeController() {
		employees.add(new Employee(1, "Sphen", null));
		employees.add(new Employee(2, "Dylan", null));
		
		// Add a manager over all the previous employees in the list
		employees.add(new Manager(3, "Bob", null, new ArrayList<Employee>(employees)));
		
		employees.add(new Employee(4, "Michael", null));
	} // end of constructor EmployeeController
	
	
	
	/** Returns a list of all employees. */
	@GetMapping() 
	public List<Employee> getAllEmployees() {
		List<Reimbursement> rems = remClient.getAllReimbursements();
		
		//Could do this efficiently with hashmaps, but this is just a demo, 
		// so O(n^2) is fine. 
		for (Employee emp : employees) {
			List<Reimbursement> empRems = new ArrayList<>();
			
			for (Reimbursement rem : rems) {
				if (rem.getRequester() == emp.getId()) {
					empRems.add(rem);
				}
			}
			
			emp.setReimbursements(empRems);
		}
		
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
		
		// If there is an employee by that id, get their reimbursements. 
		if (emp != null) {
			emp.setReimbursements(remClient.getReimbursmentsByEmpId(emp.getId()));
		}
		return emp;
	} // end of getAllEmployees
	
	
	
	
} // end of EmployeeController

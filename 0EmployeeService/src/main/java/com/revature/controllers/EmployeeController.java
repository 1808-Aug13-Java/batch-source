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

//make this controller a rest controller
@RestController
@RequestMapping(value="/employees")
public class EmployeeController {
	private Logger logger = Logger.getLogger(Employee.class.getName());
	private List<Employee> employees = new ArrayList<>();
	@Autowired
	ReimbursementClient reimbursementClient;
	
	//need a constructor for this controller to initialize its number of customers in the list (since no dao). for now keep employee list null
	public EmployeeController() {
		employees.add(new Employee(1, "Cindy Peng", "clpeng@ucdavis.edu", null));
		employees.add(new Employee(2, "Richard Johnson", "rjohnson@ucdavis.edu", null));
		employees.add(new Employee(3, "Penny Tyson", "ptyson@ucdavis.edu", null));
		employees.add(new Employee(4, "Louie Lee", "llee@ucdavis.edu", null));
	}
	//will only have 2 http methods, get all and get by id
	//get all employees
	@GetMapping
	public List<Employee> getEmployees(){
		return employees;
	}
	//get employee by id
		//eventually that employee item should also reveal the employee reimbursement objects too, which are held in lists
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id)
	{
		Employee e = employees.stream().filter(list -> list.getEmpId() == id).findFirst().get();
		e.setReimbursements( reimbursementClient.getReimbursementsOfEmp(id) );
		return e;
	}
	
	
}

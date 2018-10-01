package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.intercom.ReimbursementClient;
import com.revature.models.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private List<Employee> employees = new ArrayList<>();
	@Autowired
	ReimbursementClient reimClnt;
	public EmployeeController() {
		employees.add(new Employee(1, "Martin Smallwood", "smallwoodmartin@gmail.com",null));
		employees.add(new Employee(2, "Giant Chicken", "gchick@gmail.com",null));
		employees.add(new Employee(3, "River Troll", "rtroll@gmail.com",null));
		employees.add(new Employee(4, "Genie", "genie@gmail.com",null));
	}
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employees;
	}
	
	@GetMapping(value="/{id}")
	public Employee getEmployeeById(@PathVariable("id") int employeeId) {
		Employee employe = employees.stream().filter(emp -> emp.getEmployeeId()==employeeId).findFirst().get();
		
		employe.setReimbursements(reimClnt.getReimbursementsById(employeeId));
		return employe;
	}

}

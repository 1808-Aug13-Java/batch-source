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

@RestController
@RequestMapping("/employees")
public class EmployeeController
{
	private Logger loggy = Logger.getLogger(EmployeeController.class);
	private List<Employee> emps = new ArrayList<>();
	
	@Autowired
	ReimbursementClient rc;
	
	public EmployeeController()
	{
		emps.add(new Employee(1, "John", "Jacko", "jljacko", false));
		emps.add(new Employee(2, "Lord", "Trooper", "ltrooper", true));
		emps.add(new Employee(3, "Thor", "Odinson", "todinson", false));
		emps.add(new Employee(4, "Odin", "Borson", "oborson", true));
		emps.add(new Employee(5, "m", "cosgrove", "mtcosgrove", false));
		
	}
	
	@GetMapping
	public List<Employee> getAllEmployees()
	{
		loggy.info("EmployeeController.getAllEmployees();");
		return emps;
	}
	
	@GetMapping(value="/{id}")
	public Employee getEmployeeById(@PathVariable("id") int empId)
	{
		loggy.info("EmployeeController.getEmployeeById(" + empId + ");");
		Employee ret = emps.stream().filter(emp_ -> emp_.getId() == empId).findFirst().get();
		
		// TODO: add mechanism to retrieve the reimburements
		if(ret == null)
			return ret;
		
		ret.setRequests(rc.getAllRequests(ret.getId()));
		
		return ret;
	}
}

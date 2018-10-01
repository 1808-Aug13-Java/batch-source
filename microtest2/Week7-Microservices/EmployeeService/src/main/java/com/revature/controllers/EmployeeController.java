package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.revature.intercom.*;

import com.revature.models.*;

@RestController
@RequestMapping(value="/employees")
public class EmployeeController {
  public EmployeeController() {} 
  private List<Employee> employees = new ArrayList<>();

  @Autowired
  ReimbursementClient reimbursementClient;

  { 
    employees.add(new Employee("luke", "skywalker", "thetrueskywalker", null));
    employees.add(new Employee("obi", "wan", "kenobi", null));
    employees.add(new Employee("yoda", "??", "yoda", null));
  }
  @GetMapping
  public List<Employee> getEmployees() {
    return employees;
  }
  
  @GetMapping(value="/{username}")
  public Employee getEmployeeById(@PathVariable(name="username") String username) {
    for(Employee e : employees) {
      if(e.getUsername().equals(username)) {
        List<Reimbursement> reimbursements = reimbursementClient.getReimbursements(username);
        for(Reimbursement r : reimbursements) {
          e.setReimbursement(r);
        }
        return e;
      }
    }
    return null;
  }
}

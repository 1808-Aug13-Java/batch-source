package com.revature.reim.dao;

import java.util.List;

import com.revature.reim.model.Employee;
import com.revature.reim.model.Reimbursement;

public interface EmployeeDao {
	
	public int logIn(String username,String password);//1 for employee, 2 for man, 0 for fail
	public int logOut();
	public int submitReimbursement(int id, double amount);
	public List<Reimbursement> viewReimbursments(int empId,int choice);
	public Employee viewProfile(int id);//done
	public int changeProfile(int id,String key);
	
	
	
}

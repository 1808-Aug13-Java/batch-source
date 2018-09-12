package com.revature.reim.dao;

import java.util.List;

import com.revature.reim.model.Employee;
import com.revature.reim.model.Reimbursement;

public interface EmployeeDao {
	public int logIn(String username,String password);//1 for employee, 2 for man, 0 for fail
	public int logOut();
	public int submitReimbursement(int id, double amount);//should need username now instead
	public List<Reimbursement> viewReimbursments(int empId,int choice); //should also use username now
	public Employee viewProfile(int id);//also needs to work with the username
	public int changeProfile(int id,String key, String firstName, String lastName, String username);//also needs to work with the username
	public int getEmpId(String username);
}

package com.revature.reim.dao;

import java.util.List;

import com.revature.reim.model.Employee;
import com.revature.reim.model.Reimbursement;

public interface EmployeeDao {
	
	public int logIn(String username,String password);//done
	public int logOut();
	public int submitReimbursement(int id, double amount);
	public List<Reimbursement> viewPendingReimbursments(int id);
	public List<Reimbursement> viewResolvedReimbursements(int id);
	public Employee viewProfile(int id);//done
	public int changeProfile(int id,String key);
	
	
	
}

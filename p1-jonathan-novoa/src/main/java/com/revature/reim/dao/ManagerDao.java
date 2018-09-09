package com.revature.reim.dao;

import java.util.List;

import com.revature.reim.model.Employee;
import com.revature.reim.model.Reimbursement;

public interface ManagerDao {
//isMan=1
	public List<Employee> viewAllEmployees();//done
	public List<Reimbursement> viewAllRequest(int i);//1 for pending, 2 for resolved, done
	public int resolveRequest(int empId,int reimId,int manId, int action);//1 approve,2 deny
	public List<Reimbursement> getReimbursementsByEmp(int empId);//done
	public int createEmployee(String fname,String lname,String emial,String pass,int isMan); //done
}

package com.revature.util;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.EmpDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.models.Reimbursment;

public class ReimbursmentHelper {

	public List<Reimbursment> addEmployee(List<Reimbursment> reimbursments) {
		List<Reimbursment> copy = new ArrayList<>();
		copy.addAll(reimbursments);
		EmployeeDao ed = new EmpDaoImpl();
		for(Reimbursment r: copy) {
			Employee employee = ed.getEmployeeById(r.getEmployeeId());
			employee.setPassword("");
			r.setEmployee(employee);
			if(r.getManagerId() != 0) {
				Employee manager = ed.getEmployeeById(r.getManagerId());
				r.setManager(manager);
			}
		}
		
		return copy;
	}

}

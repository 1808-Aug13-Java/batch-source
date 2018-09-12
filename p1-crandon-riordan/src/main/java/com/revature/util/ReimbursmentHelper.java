package com.revature.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.dao.EmpDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.dao.ReimbursmentDao;
import com.revature.dao.ReimbursmentDaoImpl;
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
	
	public void createReimbursment(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Double amount = Double.parseDouble((String) request.getParameter("amount"));
		String reason = request.getParameter("reason");
		EmployeeDao ed = new EmpDaoImpl();
		Employee employee = ed.getEmployeeByEmail((String) session.getAttribute("email"));
		Integer id = employee.getId();
		Reimbursment reimbursment = new Reimbursment();
		// set a reimbursment object to create
		reimbursment.setAmount(BigDecimal.valueOf(amount));
		reimbursment.setEmployeeId(id);
		reimbursment.setReason(reason);
		
		ReimbursmentDao rd = new ReimbursmentDaoImpl();
		rd.createReimbursment(reimbursment);
	}

}

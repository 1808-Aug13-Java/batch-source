package com.revature.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImpl;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;
import com.revature.util.DBConnectionUtil;
import com.revature.util.LogUtil;
import com.revature.util.StringHasher;

public class TestMain {

	public static void main(String[] args) throws IOException, SQLException {
		LogUtil.logInfo("asdf");
		LogUtil.logDebug("asdf");
		System.out.println("Getting Connection...");
		
		// Autoclose that connection
		try (Connection con = DBConnectionUtil.getConnection()) {
			con.setAutoCommit(false);
//			// Get Dao
//			EmployeeDao empDao = new EmployeeDaoImpl();
//			
//			Employee temp = null;
//			
//			System.out.println("Querying...");
//			List<Employee> emps = empDao.getAllEmployees(con);
//			
//			for (Employee e : emps) {
//				System.out.println(e);
//			}
//			
//			System.out.println("Getting by ID...");
//			System.out.println(empDao.getEmployeeById(5, con));
//			
//			System.out.println("Bad ID returns null? " + (empDao.getEmployeeById(15, con)));
//			
//			System.out.println("By Manager ID...");
//			Manager man = new Manager();
//			man.setId(5);
//			emps = empDao.getEmployeesByManager(man, con);
//			for (int i=0; i<emps.size(); i++) {
//				System.out.println(emps.get(i));
//			}
//			
//			System.out.println("Inserting Employee...");
//			temp = new Employee(0, "Dennis", "DennisTheMeanice", "passwordHash");
//			empDao.insertEmployee(temp, con);
//			System.out.println("New Employee: " + empDao.getEmployeeById(temp.getId(), con));
//			
//			System.out.println("Updating Employee...");
//			temp.setName("Bieber");
//			temp.setUsername("JustinIsBustin");
//			temp.setPasswordHash("slightlyBetterPassword");
//			empDao.updateEmployee(temp, con);
//			System.out.println("Updated Employee: " + empDao.getEmployeeById(temp.getId(), con));
			
//			System.out.println("Inserting Employee...");
//			temp = new Employee(0, "zxcv", "asdf", StringHasher.sha256Hash("qwer"));
//			empDao.insertEmployee(temp, con);
//			System.out.println("New Employee: " + empDao.getEmployeeById(temp.getId(), con));
			
			
			
			EmployeeDao empDao = new EmployeeDaoImpl();
			ManagerDao manDao = new ManagerDaoImpl();
			LogUtil.logInfo("Manager: ");
			Employee emp = empDao.getEmployeeById(6, con);
			LogUtil.logInfo(manDao.tryCastManager(emp, con));
			
			ReimbursementDao remDao = new ReimbursementDaoImpl();
			LogUtil.logInfo("\nReimbursement Structure: ");
			for (Reimbursement reim : remDao.getAllPending(con)) {
				LogUtil.logInfo(reim);
			}
			
			LogUtil.logInfo("\nBy Id 5: " + remDao.getById(5, con));
			LogUtil.logInfo("\nBy Id -1: " + remDao.getById(-1, con));
			
			
			emp.setId(5);
			LogUtil.logInfo("\nBy Requester 5: " + remDao.getByRequester(emp, con));
			LogUtil.logInfo("\nBy Requester 5: " + remDao.getPendingByRequester(emp, con));
			
			emp.setId(-1);
			LogUtil.logInfo("\nBy Requester -1: " + remDao.getByRequester(emp, con));
			LogUtil.logInfo("\nBy Requester -1: " + remDao.getPendingByRequester(emp, con));
			
//			 Don't save any of the changes done in this test. 
			con.rollback();
		}
		
	} // end of main

}

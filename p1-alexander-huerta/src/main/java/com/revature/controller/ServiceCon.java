package com.revature.controller;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.dao.ManagerDao;
import com.revature.dao.ManagerDaoImp;
import com.revature.models.Employee;
import com.revature.models.Manager;

public class ServiceCon {

	private static Logger log = Logger.getRootLogger();

	private ServiceCon() {
		super();
	}

	public static boolean employeeLogIn(String uName, String pswd) {
		EmployeeDao empD = new EmployeeDaoImp();
		log.info("employee pswd: " + pswd);
		if (pswd == "" || uName == "") {
			return false;
		}
		Employee e = empD.getEmployeeByUserName(uName);
		if (e == null) {
			return false;
		}
		if (e.getPswrd().equals(pswd)) {
			return true;
		}
		return false;
	}

	public static boolean managerLogIn(String uName, String pswd) {
		ManagerDao manD = new ManagerDaoImp();
		
		if (pswd == "" || uName == "") {
			return false;
		}
		Manager m = manD.getManagerByUserName(uName);
		if (m == null) {
			return false;
		}
		if (m.getPswd().equals(pswd)) {
			return true;
		}
		return false;
	}

}
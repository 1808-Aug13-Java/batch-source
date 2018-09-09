package com.revature.controller;

import org.apache.log4j.Logger;

import com.revature.doa.EmployeeDAO;
import com.revature.doa.EmployeeDoaImp;
import com.revature.doa.ManagerDAO;
import com.revature.doa.ManagerDaoImpl;
import com.revature.model.Employee;
import com.revature.model.Manager;

public class ServiceController {

	private static Logger log = Logger.getRootLogger();

	private ServiceController() {
		super();
	}

	public static boolean employeeLogIn(String uName, String pswd) {
		EmployeeDAO empD = new EmployeeDoaImp();
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
		ManagerDAO manD = new ManagerDaoImpl();
		
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

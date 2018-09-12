package com.revature.reim.util;

import java.util.List;

import com.revature.reim.dao.EmployeeDao;
import com.revature.reim.dao.EmployeeDaoImpl;
import com.revature.reim.dao.ManagerDao;
import com.revature.reim.dao.ManagerDaoImpl;

public class ManagerListHelper {
	
	public static <T> List<T> getList(int choice){
		
		ManagerDao man= new ManagerDaoImpl();
//		EmployeeDao emp= new EmployeeDaoImpl();
		if( choice == 1 || choice ==2) {
			return (List<T>) man.viewAllRequest(choice);
		}
		else {
			return (List<T>) man.viewAllEmployees(choice);
		}
					
		
	}
	
	public static String getPath(int choice) {
		String result=null;
		switch(choice) {
		case 1:{
			result= "Views/PendingReim.html";
			break;
		}
		case 2:{
			result="Views/.html";
			break;
		}
			
		default:{
			result="Views/Employee.html";
		}
		}
		
		return result;
	}

}

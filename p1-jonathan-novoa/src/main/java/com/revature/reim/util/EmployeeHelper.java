package com.revature.reim.util;

public class EmployeeHelper {
	
	public static String employeeSelect(int choice) {
		String result=null;
		switch(choice) {
		case 1:{
			result= "Views/NewReim.html";
			break;
		}
		case 2:{
			result="Views/Profile.html";
			break;
		}
			
		default:{
			result="Views/Employee.html";
		}
		}
		return result;
	}

}

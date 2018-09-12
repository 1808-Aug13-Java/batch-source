package com.revature.main;

import com.revature.dao.RequestDaoImpl;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter Username: ");
//		String un = sc.nextLine();
//		System.out.println("Enter Password: ");
//		String pw = sc.nextLine();
//		EmployeeDaoImpl edi = new EmployeeDaoImpl();
//		Boolean test = mdi.validateUser(un, pw);
		
		
//		edi.updateEmployee(1, "Henry Saing", "abc", "123", "hsaing@gmail.com");
		
//		RequestDaoImpl rdi = new RequestDaoImpl();
//		System.out.println(rdi.getAllPendingRequests());
		
//		RequestDaoImpl rdi = new RequestDaoImpl();
//		rdi.updateReinbursement(0, 23);
		
		RequestDaoImpl rdi = new RequestDaoImpl();
		System.out.println(rdi.getAllRequestsFromPerson(1));

		
	}

}

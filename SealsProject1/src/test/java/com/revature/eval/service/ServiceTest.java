//package com.revature.eval.service;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//
//import com.revature.dao.EmployeeDao;
//import com.revature.dao.EmployeeDaoImpl;
//import com.revature.dao.ManagerDao;
//import com.revature.dao.ManagerDaoImpl;
//import com.revature.dao.ReimbursementDao;
//import com.revature.dao.ReimbursementDaoImpl;
//import com.revature.models.Employee;
//import com.revature.models.Manager;
//
//public class ServiceTest {
//	
//	private static final EmployeeDao evaluationEmployeeDao = new EmployeeDaoImpl();
//	private static final ManagerDao evaulationManagerDao = new ManagerDaoImpl();
//	private static final ReimbursementDao evaluationReimbursementDao = new ReimbursementDaoImpl();
//	
//	
//
//	@Test
//	public void testGetEmployeeById() {
//		Employee expectedResult = new Employee(103, "Sally Sangwine", "ssangwine3", "qRsRLQRp9", 502);
//		Employee returnedResult = evaluationEmployeeDao.getEmployeeById(103);
//		
//		assertEquals(expectedResult, returnedResult);
//	}
//	
//	@Test
//	public void testGetManagerById() {
//		Manager expectedResult = new Manager(500, "Ania Bosden", "abosden0", "zXhrbAuiRz");
//		Manager returnedResult = evaulationManagerDao.getManagerById(500);
//		
//		assertEquals(expectedResult, returnedResult);
//	}
//
//}
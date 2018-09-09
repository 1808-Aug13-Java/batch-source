//package com.revature.eval.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.sql.Connection;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//
//import com.revature.doa.EmployeeDAO;
//import com.revature.doa.EmployeeDoaImp;
//import com.revature.doa.ManagerDAO;
//import com.revature.doa.ManagerDaoImpl;
//import com.revature.doa.ReimbursmentDAO;
//import com.revature.doa.ReimbursmentDaoImpl;
//import com.revature.model.Employee;
//import com.revature.model.Manager;
//import com.revature.model.Reimbursment;
//
//public class DOATest {
//
//	private static final EmployeeDAO evaluationEmplDao = new EmployeeDoaImp();
//	private static final ManagerDAO evaluationManDao = new ManagerDaoImpl();
//	private static final ReimbursmentDAO evaluationReimbDao = new ReimbursmentDaoImpl();
//	private static Connection con = null;
//	private static Logger log = Logger.getRootLogger();
//
//	@Rule
//	public ExpectedException expectedException = ExpectedException.none();
//
//	/*
//	 * Hardcoded test case will only work with newly created database
//	 */
//	@Test
//	public void testGetEmployees() {
//		List<Employee> returnedResult = evaluationEmplDao.getEmployees();
//		List<Employee> expResult = new ArrayList<Employee>();
//
//		expResult.add(new Employee(120, "Lindsey", "Gilluley", 100, "Bryum"));
//		expResult.add(new Employee(121, "Cecelia", "Woodroffe", 101, "Vernonia"));
//		expResult.add(new Employee(122, "Brandea", "McSperron", 102, "Echinocereus"));
//		expResult.add(new Employee(123, "Owen", "Templeton", 103, "Triplaris"));
//		expResult.add(new Employee(124, "Tracie", "Runchman", 104, "Randia"));
//
//		for (int i = 0; i < expResult.size(); i++) {
//			assertTrue(returnedResult.get(i).equals(expResult.get(i)));
//		}
//
//	}
//
//	@Test
//	public void testGetEmployeesByManId() {
//		List<Employee> returnedResult = evaluationEmplDao.getEmployeesByManId(100);
//		List<Employee> expResult = new ArrayList<Employee>();
//
//		expResult.add(new Employee(120, "Lindsey", "Gilluley", 100, "Bryum"));
//
//		for (int i = 0; i < expResult.size(); i++) {
//			assertTrue(returnedResult.get(i).equals(expResult.get(i)));
//		}
//	}
//
//	@Test
//	public void testGetEmployeeById() {
//		Employee expectedResult = new Employee(120, "Lindsey", "Gilluley", 100, "Bryum");
//		Employee returnedResult = evaluationEmplDao.getEmployeeById(120);
//
//		assertEquals(expectedResult, returnedResult);
//	}
//
//	@Test
//	public void testUpdateEmployee() {
//
//	}
//
//	@Test
//	public void testCreateEmployee() {
//
//	}
//
//	@Test
//	public void testDeleteEmployee() {
//
//	}
//
//	@Test
//	public void testGetReimbursements() throws ParseException {
//		List<Reimbursment> returnedResult = evaluationReimbDao.getReimbursments();
//		List<Reimbursment> expResult = new ArrayList<Reimbursment>();
//
//		expResult.add(new Reimbursment(123, 120, "pending", "luctus rutrum nulla tellus in", 0));
//		expResult.add(new Reimbursment(124, 121, "pending", "ante ipsum primis", 0));
//		expResult.add(new Reimbursment(125, 121, "resolved",
//				"nam nulla integer pede justo lacinia eget tincidunt eget tempus vel", 101));
//
//		for (int i = 0; i < expResult.size(); i++) {
//			assertTrue(returnedResult.get(i).equals(expResult.get(i)));
//		}
//	}
//
//	@Test
//	public void testGetReimbursementById() throws ParseException {
//
//		Reimbursment expectedResult = new Reimbursment(123, 120, "pending", "luctus rutrum nulla tellus in", 0);
//		Reimbursment returnedResult = evaluationReimbDao.getReimbursmentById(123);
//
//		assertEquals(expectedResult, returnedResult);
//	}
//
//	@Test
//	public void testGetReimbursementByEmpId() throws ParseException {
//		List<Reimbursment> returnedResult = evaluationReimbDao.getReimbursmentByEmpId(121);
//		List<Reimbursment> expResult = new ArrayList<Reimbursment>();
//
//		expResult.add(new Reimbursment(124, 121, "pending", "ante ipsum primis", 0));
//		expResult.add(new Reimbursment(125, 121, "resolved",
//				"nam nulla integer pede justo lacinia eget tincidunt eget tempus vel", 101));
//
//		for (int i = 0; i < expResult.size(); i++) {
//			assertTrue(returnedResult.get(i).equals(expResult.get(i)));
//		}
//	}
//
//	@Test
//	public void testGetReimbursementByManId() {
//		List<Reimbursment> returnedResult = evaluationReimbDao.getReimbursmentByManId(101);
//		List<Reimbursment> expResult = new ArrayList<Reimbursment>();
//
//		expResult.add(new Reimbursment(125, 121, "resolved",
//				"nam nulla integer pede justo lacinia eget tincidunt eget tempus vel", 101));
//
//		for (int i = 0; i < expResult.size(); i++) {
//			assertTrue(returnedResult.get(i).equals(expResult.get(i)));
//		}
//	}
//
//	@Test
//	public void testGetReimbursementByStatus() {
//		List<Reimbursment> returnedResult = evaluationReimbDao.getReimbursmentByStatus("pending");
//		List<Reimbursment> expResult = new ArrayList<Reimbursment>();
//
//		expResult.add(new Reimbursment(123, 120, "pending", "luctus rutrum nulla tellus in", 0));
//		expResult.add(new Reimbursment(124, 121, "pending", "ante ipsum primis", 0));
//
//		for (int i = 0; i < expResult.size(); i++) {
//			assertTrue(returnedResult.get(i).equals(expResult.get(i)));
//		}
//	}
//
//	@Test
//	public void testUpdateReimbursement() {
//
//	}
//
//	@Test
//	public void testCreateReimbursement() {
//
//	}
//
//	@Test
//	public void testGetManagerById() {
//		Manager returnedResult = evaluationManDao.getManagerById(100);
//		Manager expResult = new Manager(100, "Francesca", "McKeggie");
//
//		assertTrue(returnedResult.equals(expResult));
//	}
//
//	@Test
//	public void testGetManagers() {
//		List<Manager> returnedResult = evaluationManDao.getManagers();
//		List<Manager> expResult = new ArrayList<Manager>();
//
//		expResult.add(new Manager(100, "Francesca", "McKeggie"));
//		expResult.add(new Manager(101, "Marchall", "McKeaveney"));
//		expResult.add(new Manager(102, "Arron", "Picker"));
//		expResult.add(new Manager(103, "Kalila", "Waistell"));
//		expResult.add(new Manager(104, "Cointon", "Piperley"));
//		expResult.add(new Manager(105, "Mollee", "Eatherton"));
//		expResult.add(new Manager(106, "Thornton", "Lawland"));
//		expResult.add(new Manager(107, "Shermy", "Snedker"));
//		expResult.add(new Manager(108, "Merla", "Midlar"));
//		expResult.add(new Manager(109, "Waylan", "Davidek"));
//
//		for (int i = 0; i < expResult.size(); i++) {
//			assertTrue(returnedResult.get(i).equals(expResult.get(i)));
//		}
//	}
//
//	@Test
//	public void testUpdateManager() {
//
//	}
//
//}

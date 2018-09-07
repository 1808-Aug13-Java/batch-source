package com.revature.eval.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.doa.EmployeeDOA;
import com.revature.doa.EmployeeDoaImp;
import com.revature.doa.ManagerDAO;
import com.revature.doa.ManagerDAOImpl;
import com.revature.doa.ReimbursmentDAO;
import com.revature.doa.ReimbursmentDAOImpl;
import com.revature.model.Employee;


public class ServicesTest {

	private static final EmployeeDOA evaluationEmplDao = new EmployeeDoaImp();
	private static final ManagerDAO evaluationManDao = new ManagerDAOImpl();
	private static final ReimbursmentDAO evaluationReimbDao = new ReimbursmentDAOImpl();
	private static Connection con = null;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	/*
	 * Hardcoded test case will only work with newly created database
	 */
	@Test
	public void testGetEmployees() {
		List<Employee> expectedResult = new ArrayList<Employee>();
		List<Employee> returnedResult = evaluationEmplDao.getEmployees();
		
		expectedResult.add(new Employee(120, "Lindsey", "Gilluley", 100, "Byrum"));
		expectedResult.add(new Employee(121, "Cecelia", "Woodroffe", 101, "Vernonia"));
		expectedResult.add(new Employee(122, "Brandea", "McSperron", 102, "Echinocereus"));
		expectedResult.add(new Employee(123, "Owen", "Templeton", 103, "Triplaris"));
		expectedResult.add(new Employee(124, "Tracie", "Runchman", 104, "Randia"));
		
		
		assertEquals(expectedResult, returnedResult);
	}
	
	@Test
	public void testGetEmployeeById() {
		Employee expectedResult = new Employee(120, "Lindsey", "Gilluley", 100, "Bryum");
		Employee returnedResult = evaluationEmplDao.getEmployeeById(120);
		
		assertEquals(expectedResult, returnedResult);
	}	
}

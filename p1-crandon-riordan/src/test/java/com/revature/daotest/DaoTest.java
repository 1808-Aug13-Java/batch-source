package com.revature.daotest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.revature.dao.EmpDaoImpl;
import com.revature.dao.ReimbursmentDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Reimbursment;
import com.revature.util.ConnectionUtil;
import com.revature.util.Hasher;

public class DaoTest {

	
	static final Logger logger = Logger.getLogger(DaoTest.class);
	
	@Test
	public void canCreateManager() {
		Hasher hasher = new Hasher();
		try(Connection con = ConnectionUtil.getConnection()) {
			con.commit();
			con.setAutoCommit(false);
			EmpDaoImpl edi = new EmpDaoImpl();
			Employee employee = new Employee();
			employee.setId(52125);
			employee.setName("Karen Goslo");
			employee.setIsManager(1);
			employee.setUsername("kgoslo");
			employee.setPassword(hasher.getHashPassword("password"));
			employee.setEmail("kgoslo@gmail.com");
			assertEquals(1, edi.createEmployee(employee));
			con.rollback();
		} catch (SQLException | IOException e) {
			logger.info("createUserWorks() test threw exception " + e.getMessage());
		}
	}
	
	@Test
	public void canCreateEmployee() {
		Hasher hasher = new Hasher();
		
		try(Connection con = ConnectionUtil.getConnection()) {
			con.commit();
			con.setAutoCommit(false);
			EmpDaoImpl edi = new EmpDaoImpl();
			Employee employee = new Employee();
			employee.setId(52121);
			employee.setName("Karen Goslo");
			employee.setIsManager(0);
			employee.setUsername("kgoslo");
			employee.setPassword(hasher.getHashPassword("password"));
			employee.setEmail("kgoslo@gmail.com");
			assertEquals(1, edi.createEmployee(employee));
			con.rollback();
		} catch (SQLException | IOException e) {
			logger.info("createUserWorks() test threw exception " + e.getMessage());
		}
	}
	
	@Test
	public void canCreateReimbursment() {
		try(Connection con = ConnectionUtil.getConnection()) {
			con.commit();
			con.setAutoCommit(false);
			Reimbursment reimbursment = new Reimbursment();
			reimbursment.setAmount(new BigDecimal(500));
			reimbursment.setCurrentState("pending");
			reimbursment.setEmployeeId(52121);
			reimbursment.setManagerId(51215);
			reimbursment.setReimbursmentId(1000);
			
			ReimbursmentDaoImpl rdi = new ReimbursmentDaoImpl();
			assertEquals(1, rdi.createReimbursment(reimbursment));
			con.rollback();
		} catch (SQLException | IOException e) {
			logger.info("test threw exception " + e.getMessage());
		}
		
	}
	
	@Test
	public void canCreateReimbursmentWithIncompleteData() {
		try(Connection con = ConnectionUtil.getConnection()) {
			con.commit();
			con.setAutoCommit(false);
			Reimbursment reimbursment = new Reimbursment();
			reimbursment.setAmount(new BigDecimal(24124));
			reimbursment.setEmployeeId(52121);
			
			ReimbursmentDaoImpl rdi = new ReimbursmentDaoImpl();
			assertEquals(1, rdi.createReimbursment(reimbursment));
			con.rollback();
		} catch (SQLException | IOException e) {
			logger.info("test threw exception " + e.getMessage());
		}
		
	}
	
	@Test 
	public void canRetrieveEmployeeByEmail() {
		EmpDaoImpl edi = new EmpDaoImpl();
		Employee emp = edi.getEmployeeByEmail("example1@gmail.com");
		assertEquals("example1", emp.getUsername());
	}
	
	@Test 
	public void canRetrieveManagerByEmail() {
		EmpDaoImpl edi = new EmpDaoImpl();
		Employee emp = edi.getEmployeeByEmail("michaelscott@gmail.com");
		assertEquals("michaelscott", emp.getUsername());
	}
	
	
//	----------------------------
//	Testing hashing
//	----------------------------
	@Test
	public void hashingWorksWithSamePassword() {
		Hasher hasher = new Hasher();
		String password = "theoffice";
		String hashedPassword = hasher.getHashPassword(password);
		System.out.println(hashedPassword);
		assertTrue(hasher.getHashPassword("theoffice").equals(hashedPassword));
	}

}

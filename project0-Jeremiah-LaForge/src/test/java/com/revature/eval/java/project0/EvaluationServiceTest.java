package com.revature.eval.java.project0;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.bank.dao.AccountDAO;
import com.revature.bank.dao.AccountDaoImp;
import com.revature.bank.dao.UserDAO;
import com.revature.bank.dao.UserDaoImp;
import com.revature.bank.util.ConnectionUtil;
import com.revature.model.Account;
import com.revature.model.User;

public class EvaluationServiceTest {

	private static final UserDAO evaluationUserDao = new UserDaoImp();
	private static final AccountDAO evaluationAccDao = new AccountDaoImp();
	private static Connection con = null;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCreateUserP1() throws IOException, SQLException {
		con = ConnectionUtil.getConnection();
		con.setAutoCommit(false);
		User user = new User("RonSwanson", "Ron", "Swanson", "goodpassword");
		assertEquals(1, evaluationUserDao.createUser(user, con));
	}

	@Test
	public void testCreateUserp2() throws IOException, SQLException {
		con = ConnectionUtil.getConnection();
		con.setAutoCommit(false);
		User user = evaluationUserDao.getUserByName("RonSwanson", con);
		assertEquals(user, new User("RonSwanson", "Ron", "Swanson", "goodpassword"));
	}

	@Test
	public void testUpdateUser() throws IOException, SQLException {
		con = ConnectionUtil.getConnection();
		con.setAutoCommit(false);
		UserDAO updUser = new UserDaoImp();
		User user = new User("RonSwanson", "Ron", "Swanson", "badpassword");
		evaluationUserDao.updateAccount(user, con);
		assertEquals(user, updUser.getUserByName("RonSwanson", con));
	}

	@Test
	public void testCreateAccount() throws IOException, SQLException {
		con = ConnectionUtil.getConnection();
		con.setAutoCommit(false);
		assertEquals(1, evaluationAccDao.createAccount(new Account("RonSwanson", 100), con));
	}

	@Test
	public void testGetAccountByUser() throws IOException, SQLException {
		con = ConnectionUtil.getConnection();
		con.setAutoCommit(false);
		Account acc = new Account("RonSwanson", 100);
		User user = new User("RonSwanson", "Ron", "Swanson", "goodpassword");
		evaluationUserDao.createUser(user, con);
		evaluationAccDao.createAccount(new Account("RonSwanson", 100), con);
		assertEquals(acc, evaluationAccDao.getAccountByUser("RonSwanson", con));
	}

	@Test
	public void testUpdateAccount() throws IOException, SQLException {
		con = ConnectionUtil.getConnection();
		con.setAutoCommit(false);
		Account acc = new Account("RonSwanson", 200);
		AccountDAO accountDI = new AccountDaoImp();
		evaluationAccDao.updateAccount(acc, con);
		assertEquals(acc, accountDI.getAccountByUser("RonSwanson", con));
	}

}

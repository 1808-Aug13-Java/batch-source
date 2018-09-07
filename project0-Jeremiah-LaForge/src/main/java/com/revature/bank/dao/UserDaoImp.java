package com.revature.bank.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.bank.util.ConnectionUtil;
import com.revature.model.User;

public class UserDaoImp implements UserDAO {

	private static Logger log = Logger.getRootLogger();
	private static final String SQLIOERR = "Connection failed, or SQL error.";
	private static final String USERNAME = "USER_NAME";
	private static final String FNAME = "FNAME";
	private static final String LNAME = "LNAME";
	private static final String PSWD = "PSWD";

	@Override
	public Set<User> getUsers() {
		Set<User> userList = new HashSet<>();

		String sql = "SELECT * FROM BANK_USER";

		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)) {

			while (rs.next()) {
				User user = new User();

				user.setUserName(rs.getString(USERNAME));
				user.setfName(rs.getString(FNAME));
				user.setlName(rs.getString(LNAME));
				user.setPsWord(PSWD);

				userList.add(user);
			}

		} catch (IOException | SQLException e) {
			log.error(SQLIOERR, e);
		}

		return userList;
	}
	
	@Override
	public User getUserByName(String name) {
		String sql = "SELECT * FROM BANK_USER WHERE USER_NAME = ?";
		User user = null;
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, name);
			rs = ps.executeQuery();

			while (rs.next()) {
				user = new User();

				user.setUserName(rs.getString(USERNAME));
				user.setfName(rs.getString(FNAME));
				user.setlName(rs.getString(LNAME));
				user.setPsWord(rs.getString(PSWD));
			}

		} catch (IOException | SQLException e) {
			log.error(SQLIOERR, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error("rs failed to close.", e);
			}
		}

		return user;
	}

	@Override
	public User getUserByName(String name, Connection con) {
		String sql = "SELECT * FROM BANK_USER WHERE USER_NAME = ?";
		User user = null;
		ResultSet rs = null;

		try (PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, name);
			rs = ps.executeQuery();

			while (rs.next()) {
				user = new User();

				user.setUserName(rs.getString(USERNAME));
				user.setfName(rs.getString(FNAME));
				user.setlName(rs.getString(LNAME));
				user.setPsWord(rs.getString(PSWD));
			}

		} catch (SQLException e) {
			log.error(SQLIOERR, e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error("rs failed to close.", e);
			}
		}

		return user;
	}
	
	@Override
	public int createUser(User user) {
		String sql = "INSERT INTO BANK_USER (USER_NAME, FNAME, LNAME, PSWD) VALUES (?, ?, ?, ?) ";
		int userCreated = 0;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getfName());
			ps.setString(3, user.getlName());
			ps.setString(4, user.getPsWord());
			userCreated = ps.executeUpdate();

		} catch (SQLException | IOException e) {
			log.error(SQLIOERR, e);
		}
		return userCreated;
	}

	/*
	 * User can not change their user settings this iteration. User_name changing
	 * not supported this iteration. Better practice is to have a unique user id,
	 * but due to time constraints this will be left out as it was not required that
	 * user be able to edit their information (non-Javadoc)
	 * 
	 * @see com.revature.bank.dao.UserDAO#updateAccount(com.revature.bank.user.User)
	 */
	@Override
	public int updateAccount(User user) {
		int userUpdated = 0;

		String sql = "UPDATE BANK_USER " + "SET FNAME = ?," + " LNAME = ?," + " PSWD = ?" + "WHERE USER_NAME = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, user.getfName());
			ps.setString(2, user.getlName());
			ps.setString(3, user.getPsWord());
			ps.setString(4, user.getUserName());
			userUpdated = ps.executeUpdate();

		} catch (SQLException | IOException e) {
			log.error(SQLIOERR, e);
		}

		return userUpdated;
	}

	/*
	 * (non-Javadoc)
	 * This calls a stored procedure to delete the user. 
	 * On cascade should delete the associated bank account as well
	 * @see com.revature.bank.dao.UserDAO#deletUserByName(java.lang.String)
	 */
	@Override
	public int deletUserByName(String name) {
		int rowsDeleted = 0;

		try (Connection con = ConnectionUtil.getConnection();
				CallableStatement cStmt = con.prepareCall("{CALL DELETE_USERANDACC(?)}");) {
			cStmt.setString(1, name);
			rowsDeleted = cStmt.executeUpdate();

		} catch (SQLException | IOException e) {
			log.error(SQLIOERR, e);
		}

		return rowsDeleted;
	}

	@Override
	public int createUser(User user, Connection con) {
		String sql = "INSERT INTO BANK_USER (USER_NAME, FNAME, LNAME, PSWD) VALUES (?, ?, ?, ?) ";
		int userCreated = 0;

		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getfName());
			ps.setString(3, user.getlName());
			ps.setString(4, user.getPsWord());
			userCreated = ps.executeUpdate();

		} catch (SQLException e) {
			log.error(SQLIOERR, e);
		}
		return userCreated;
	}

	@Override
	public int updateAccount(User user, Connection con) {
		int userUpdated = 0;

		String sql = "UPDATE BANK_USER " + "SET FNAME = ?," + " LNAME = ?," + " PSWD = ?" + "WHERE USER_NAME = ?";

		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, user.getfName());
			ps.setString(2, user.getlName());
			ps.setString(3, user.getPsWord());
			ps.setString(4, user.getUserName());
			userUpdated = ps.executeUpdate();

		} catch (SQLException e) {
			log.error(SQLIOERR, e);
		}

		return userUpdated;
	}

}

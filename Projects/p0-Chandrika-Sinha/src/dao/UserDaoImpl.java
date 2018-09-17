package dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.User;
import util.ConnectionUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public List<User> getUsers() {
		List<User> userList = new ArrayList<>();
		String sql = "SELECT * FROM BANKUSER";
		
		// ok to use statement because we're not taking any user input
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql); ) {
			while (rs.next()) {
				User u = new User();
				String username = rs.getString("BANK_USERNAME");
				String password = rs.getString("BANK_PASSWORD");
				String firstname = rs.getString("FIRSTNAME");
				String lastname = rs.getString("LASTNAME");
				
				u.setUsername(username);
				u.setPassword(password);
				u.setFirstname(firstname);
				u.setLastname(lastname);
				
				userList.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userList;
	}
	
	public List<Account> getAcounts(String userId) {
		List<Account> userAccounts = new ArrayList<>();
		// ok to use statement because we're not taking any user input
		String sql = "{CALL GET_USER_ACCOUNTS(?)}";
		try (Connection con = ConnectionUtil.getConnection();
			CallableStatement cs = con.prepareCall(sql); ) {
			ResultSet rs = cs.getResultSet();
			while (rs.next()) {
				Account a = new Account();
				
				int id = rs.getInt("BANKACCOUNT_ID");
				BigDecimal balance = rs.getBigDecimal("BANKACCOUNT_BALANCE");
				String type = rs.getString("CHECKING_OR_SAVINGS");
				boolean isJoint = rs.getString("SINGLE_OR_JOINT").equals("joint");

				a.setId(id);
				a.setBalance(balance);
				a.setType(type);
				a.setJoint(isJoint);

				userAccounts.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userAccounts;
	}

	@Override
	public User getUserById(String id) {
		User u = null;
		String sql = "SELECT * FROM BANKUSER WHERE BANK_USERNAME = ?";
		
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String username = rs.getString("BANK_USERNAME");
				String password = rs.getString("BANK_PASSWORD");
				String firstname = rs.getString("FIRSTNAME");
				String lastname = rs.getString("LASTNAME");
				
				u = new User(username, password, firstname, lastname);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return u;
	}

	@Override
	public User getUserById(Connection con, String id) {
		User u = null;
		String sql = "SELECT * FROM DEPARMENT WHERE DEPT_ID = ?";
		
		ResultSet rs = null;
		try(PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String username = rs.getString("BANK_USERNAME");
				String password = rs.getString("BANK_PASSWORD");
				String firstname = rs.getString("FIRSTNAME");
				String lastname = rs.getString("LASTNAME");
				
				u = new User(username, password, firstname, lastname);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return u;
	}

	@Override
	public int createUser(User user) {
		int usersCreated = 0;
		String sql = "INSERT INTO BANKUSER (BANK_USERNAME, BANK_PASSWORD, FIRSTNAME, LASTNAME) VALUES (?, ?, ?, ?)";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			usersCreated = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersCreated;
	}

	@Override
	public int updateUser(User user) {
		int usersUpdated = 0;
		String sql = "UPDATE BANKUSER "
				+ "SET BANK_USERNAME = ?, "
				+ "BANK_PASSWORD = ?, "
				+ "FIRSTNAME = ?, "
				+ "LASTNAME = ? ";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			
			usersUpdated = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersUpdated;
	}

	@Override
	public int deleteUser(User user) {
		int rowsDeleted = 0;
		String sql = "DELETE FROM BANKUSER WHERE BANK_USERNAME = ?";
		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql); ) {
			ps.setString(1, user.getUsername());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsDeleted;
	}

	@Override
	public int deleteUserById(String id) {
		int rowsDeleted = 0;
		String sql = "DELETE FROM BANKUSER WHERE BANK_USERNAME = ?";
		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql); ) {
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsDeleted;
	}
}

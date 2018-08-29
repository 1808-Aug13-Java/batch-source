package dao;

import java.io.IOException;
import java.math.BigDecimal;
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

public class AccountDaoImpl implements AccountDao {

	
	@Override
	public List<Account> getAccounts() {
		List<Account> accountList = new ArrayList<>();
		String sql = "SELECT * FROM BANKACCOUNT";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			Account a = new Account();
			while (rs.next()) {
				int id = rs.getInt("BANKACCOUNT_ID");
				BigDecimal balance = rs.getBigDecimal("BANKACCOUNT_BALANCE"); 
				String type = rs.getString("CHECKING_OR_SAVINGS");
				String jointOrSingle = rs.getString("SINGLE_OR_JOINT");
				a.setId(id);
				a.setBalance(balance);
				a.setType(type);
				a.setJoint(jointOrSingle.equals("joint"));
				accountList.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return accountList;
	}
	
	@Override
	public int getNextAccountId() {
		int accountId = -1;
		
		if (getAccounts().size()==0) {
			return 0;
		}
		
		String sql = "SELECT MAX(BANKACCOUNT_ID) AS MAX_ACCT_ID FROM BANKACCOUNT";
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql);) {
			if (rs.next()) {
				accountId = rs.getInt("MAX_ACCT_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accountId + 1;
	}

	@Override
	public Account getAccountById(int id) {
		Account a = null;
		String sql = "SELECT * FROM BANKACCOUNT WHERE BANKACCOUNT_ID = ?";
		
		ResultSet rs = null;
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int accountId = rs.getInt("BANKACCOUNT_ID");
				BigDecimal balance = rs.getBigDecimal("BANKACCOUNT_BALANCE");
				String type = rs.getString("CHECKING_OR_SAVINGS");
				boolean isJoint = rs.getString("SINGLE_OR_JOINT").equals("joint");
				
				a = new Account(accountId, balance, type, isJoint);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public Account getAccountById(Connection con, int id) {
		Account a = null;
		String sql = "SELECT * FROM BANKACCOUNT WHERE BANKACCOUNT_ID = ?";
		
		ResultSet rs = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int accountId = rs.getInt("BANKACCOUNT_ID");
				BigDecimal balance = rs.getBigDecimal("BANKACCOUNT_BALANCE");
				String type = rs.getString("CHECKING_OR_SAVINGS");
				boolean isJoint = rs.getString("SINGLE_OR_JOINT").equals("joint");
				
				a = new Account(accountId, balance, type, isJoint);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public int createAccount(Account account, User user) {
		int accountsCreated = 0;
		String sql = "INSERT INTO BANKACCOUNT (BANKACCOUNT_ID, BANKACCOUNT_BALANCE, CHECKING_OR_SAVINGS, SINGLE_OR_JOINT) VALUES (?, ?, ?, ?)";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql); ) {
			ps.setInt(1, getNextAccountId());
			ps.setBigDecimal(2, account.getBalance());
			ps.setString(3, account.getType());
			String isJoint = account.isJoint() ? "joint" : "single";
			ps.setString(4, isJoint);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String sql2 = "INSERT INTO BANKUSER_BANKACCOUNT (BANK_USERNAME, BANKACCOUNT_ID) VALUES (?, ?)";
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql2); ) {
			ps.setInt(1, account.getId());
			ps.setString(2, user.getUsername());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accountsCreated;
	}

	@Override
	public int updateAccount(Account account) {
		int usersUpdated = 0;
		String sql = "UPDATE BANKACCOUNT "
				+ "SET BANKACCOUNT_ID = ?, "
				+ "BANKACCOUNT_BALANCE = ?, "
				+ "CHECKING_OR_SAVINGS = ?, "
				+ "SINGLE_OR_JOINT = ? ";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			con.setAutoCommit(false);
			ps.setInt(1, account.getId());
			ps.setBigDecimal(2, account.getBalance());
			ps.setString(3, account.getType());
			String isJoint = account.isJoint() ? "joint" : "single";
			ps.setString(4, isJoint);
			
			usersUpdated = ps.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return usersUpdated;
	}

	@Override
	public int deleteAccount(Account account) {
		int rowsDeleted = 0;
		String sql = "DELETE FROM BANKACCOUNT WHERE BANKACCOUNT_ID = ?";
		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql); ) {
			ps.setInt(1, account.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return rowsDeleted;
	}

	@Override
	public int deleteAccountById(int id) {
		int rowsDeleted = 0;
		String sql = "DELETE FROM BANKACCOUNT WHERE BANKACCOUNT_ID = ?";
		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql); ) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return rowsDeleted;
	}

}

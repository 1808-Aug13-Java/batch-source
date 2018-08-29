package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Balance;
import com.revature.model.Login;
import com.revature.util.ConnectionUtil;

public class BalanceDaoImpl implements BalanceDao{
	private static Logger log = Logger.getRootLogger();
	
	@Override
	public List<Balance> getBalances() {
		List<Balance> balanceList = new ArrayList<>();
		Balance b = null;
		
		String sql = "SELECT * FROM BALANCE";
		ResultSet rs = null;
		try(Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement()){
			rs = s.executeQuery(sql);
			
			while (rs.next()) {
				String username = rs.getString("USERNAME");
				BigDecimal money = rs.getBigDecimal("BALANCE");
				b = new Balance(username, money);
				balanceList.add(b);
			}
		} catch (SQLException | IOException e) {
			log.error(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e);
			}
		}
		
		return balanceList;
	}

	@Override
	public Balance getBalanceById(String id) {
		Balance b = null;
		String sql = "SELECT * FROM BALANCE WHERE USERNAME = ?";
		ResultSet rs = null;
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String username = rs.getString("USERNAME");
				BigDecimal balance = rs.getBigDecimal("BALANCE");
				b = new Balance(username, balance);
			}
		} catch (IOException | SQLException e) {
			log.error(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e);
			}
		}
		return b;
	}

	@Override
	public Balance getBalanceById(String id, Connection con) {
		Balance b = null;
		String sql = "SELECT * FROM BALANCE WHERE USERNAME = ?";
		ResultSet rs = null;
		
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				String username = rs.getString("USERNAME");
				BigDecimal balance = rs.getBigDecimal("BALANCE");
				b = new Balance(username, balance);
			}
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error(e);
			}
		}
		return b;
	}

	@Override
	public int createBalance(Balance balance) {
		int balancesCreated = 0;
		String sql = "INSERT INTO BALANCE (USERNAME, BALANCE) VALUES (?, ?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, balance.getUsername());
			ps.setBigDecimal(2, balance.getMoney());
			balancesCreated = ps.executeUpdate();
		} catch (IOException | SQLException e) {
			log.error(e);
		}
		return balancesCreated;
	}

	@Override
	public int updateBalance(Balance balance) {
		int balancesUpdated = 0;
		String sql = "UPDATE BALANCE "
				+ "SET BALANCE = ? "
				+ "WHERE USERNAME = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setBigDecimal(1, balance.getMoney());
			ps.setString(2, balance.getUsername());
			balancesUpdated = ps.executeUpdate();
		} catch (IOException | SQLException e) {
			log.error(e);
		}
		return balancesUpdated;
	}

	@Override
	public int deleteBalanceById(String id) {
		int rowsDeleted = 0;
		String sql = "DELETE FROM BALANCE WHERE USERNAME = ?";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, id);
			rowsDeleted = ps.executeUpdate();
		} catch (IOException | SQLException e) {
			log.error(e);
		}
		return rowsDeleted;
	}

}

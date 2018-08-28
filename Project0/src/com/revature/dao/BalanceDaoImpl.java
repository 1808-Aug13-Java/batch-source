package com.revature.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Balance;
import com.revature.model.Login;
import com.revature.util.ConnectionUtil;

public class BalanceDaoImpl implements BalanceDao{

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
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return balanceList;
	}

	@Override
	public Balance getBalanceById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Balance getBalanceById(String id, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createBalance(Balance balance) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBalance(Balance balance) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBalanceById(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}

package com.revature.doa;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Manager;
import com.revature.util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDAO {

	private static Logger log = Logger.getRootLogger();

	@Override
	public List<Manager> getManagers() {
		List<Manager> manList = new ArrayList<>();
		String sql = "SELECT * FROM MANAGER";

		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			while (rs.next()) {
				Manager m = new Manager();
				m.setManId(rs.getInt("MAN_ID"));
				m.setName(rs.getString("NAME"));
				m.setPswd(rs.getString("PSWD"));
				m.setUserName(rs.getString("USERNAME"));
				manList.add(m);
			}
		} catch (IOException | SQLException a) {
			log.error(a);
		}
		return manList;
	}

	@Override
	public Manager getManagerById(int id) {

		String sql = "SELECT * FROM MANAGER WHERE MAN_ID = ?";
		Manager m = null;
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				m = new Manager();
				m.setManId(rs.getInt("MAN_ID"));
				m.setName(rs.getString("NAME"));
				m.setPswd(rs.getString("PSWD"));
				m.setUserName(rs.getString("USERNAME"));
			}
		} catch (SQLException | IOException a) {
			log.error(a);
		} finally {
			if (rs != null) {
				try {
					if (!rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException a) {
					log.error(a);
				}
			}
		}
		return m;
	}

	@Override
	public int updateManager(Manager manager) {
		int managerUpdated = 0;

		String sql = "UPDATE MANAGER " + "SET NAME = ?, " + "PSWD = ? " + "WHERE MAN_ID = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, manager.getName());
			ps.setString(2, manager.getPswd());
			ps.setInt(3, manager.getManId());
			managerUpdated = ps.executeUpdate();

		} catch (SQLException | IOException a) {
			log.error(a);
		}

		return managerUpdated;
	}

	@Override
	public int updateManager(Manager manager, Connection con) {
		int managerUpdated = 0;

		String sql = "UPDATE MANAGER " + "SET NAME = ?, " + "PSWD = ? " + "WHERE MAN_ID = ?";

		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, manager.getName());
			ps.setString(2, manager.getPswd());
			ps.setInt(3, manager.getManId());
			managerUpdated = ps.executeUpdate();

		} catch (SQLException a) {
			log.error(a);
		}

		return managerUpdated;
	}

	@Override
	public Manager getManagerByUserName(String userName) {
		String sql = "SELECT * FROM MANAGER WHERE USERNAME = ?";
		Manager m = null;
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, userName);
			rs = ps.executeQuery();

			while (rs.next()) {
				m = new Manager();
				m.setManId(rs.getInt("MAN_ID"));
				m.setName(rs.getString("NAME"));
				m.setPswd(rs.getString("PSWD"));
				m.setUserName(rs.getString("USERNAME"));
			}
		} catch (SQLException | IOException a) {
			log.error(a);
		} finally {
			if (rs != null) {
				try {
					if (!rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException a) {
					log.error(a);
				}
			}
		}
		return m;
	}
}

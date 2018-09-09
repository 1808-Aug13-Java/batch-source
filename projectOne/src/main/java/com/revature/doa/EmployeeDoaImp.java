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

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDoaImp implements EmployeeDAO {
	
	private static Logger log = Logger.getRootLogger();
	
	@Override
	public List<Employee> getEmployees() {

		List<Employee> employeeList = new ArrayList<>();
		String sql = "SELECT * FROM EMPLOYEE";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)) {
			while (rs.next()) {
				Employee e = new Employee();
				e.setEmpId(rs.getInt("EMP_ID"));
				e.setfName(rs.getString("FNAME"));
				e.setlName(rs.getString("LNAME"));
				e.setManId(rs.getInt("MAN_ID"));
				e.setPswrd(rs.getString("PSWRD"));
				e.setUserName(rs.getString("USERNAME"));
				employeeList.add(e);
			}
		} catch (IOException | SQLException a) {
			log.error(a);
		}
		return employeeList;
	}

	@Override
	public Employee getEmployeeById(int id) {
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		Employee e = null;
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				e = new Employee();
				e.setEmpId(rs.getInt("EMP_ID"));
				e.setfName(rs.getString("FNAME"));
				e.setlName(rs.getString("LNAME"));
				e.setManId(rs.getInt("MAN_ID"));
				e.setPswrd(rs.getString("PSWRD"));
				e.setUserName(rs.getString("USERNAME"));
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
		return e;
	}

	@Override
	public List<Employee> getEmployeesByManId(int manId) {
		
		List<Employee> employeeList = new ArrayList<>();
		String sql = "SELECT * FROM EMPLOYEE WHERE MAN_ID = ?";
		
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, manId);
			rs = ps.executeQuery();

			while (rs.next()) {
				Employee e = new Employee();
				e.setEmpId(rs.getInt("EMP_ID"));
				e.setfName(rs.getString("FNAME"));
				e.setlName(rs.getString("LNAME"));
				e.setManId(rs.getInt("MAN_ID"));
				e.setPswrd(rs.getString("PSWRD"));
				e.setUserName(rs.getString("USERNAME"));
				employeeList.add(e);
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
		return employeeList;
	}
	
	@Override
	public int createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmployee(Employee employee) {
		int emplUpdated = 0;

		String sql = "UPDATE EMPLOYEE " + "SET FNAME = ?, " + "LNAME = ?, " + "PSWRD = ? " + "WHERE EMP_ID = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, employee.getfName());
			ps.setString(2, employee.getlName());
			ps.setString(3, employee.getPswrd());
			ps.setInt(4, employee.getEmpId());
			emplUpdated = ps.executeUpdate();

		} catch (SQLException | IOException a) {
			log.error(a);
		}

		return emplUpdated;
	}

	@Override
	public int deleteEmployeeById(int id) {
		int emplDeleted = 0;

		String sql = "DELETE FROM EMPLOYEE WHERE EMP_ID = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			emplDeleted = ps.executeUpdate();

		} catch (SQLException | IOException a) {
			log.error(a);
		}

		return emplDeleted;
	}

	@Override
	public int createEmployee(Employee employee, Connection con) {
		int employeesCreated = 0;
		String sql = "INSERT INTO EMPLOYEE (FNAME, LNAME, MAN_ID, PSWRD) VALUES (?, ?, ?, ?)";
		//call stored procedure to create username!!

		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, employee.getfName());
			ps.setString(2, employee.getlName());
			ps.setInt(3, employee.getManId());
			ps.setString(4, employee.getPswrd());
			employeesCreated = ps.executeUpdate();

		} catch (SQLException a) {
			log.error(a);
		}
		
		sql = "{call TR_INSERT_EMP_USERNAME()}";

		try (CallableStatement cs = con.prepareCall(sql);) {

		} catch (SQLException e) {
			log.error("Stored procedure failed: create employee username "+ e.getMessage());
		}
		
		return employeesCreated;
	}

	@Override
	public int updateEmployee(Employee employee, Connection con) {
		int emplUpdated = 0;

		String sql = "UPDATE EMPLOYEE " + "SET FNAME = ?, " + "LNAME = ?, " + "PSWRD = ? " + "WHERE EMP_ID = ?";

		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, employee.getfName());
			ps.setString(2, employee.getlName());
			ps.setString(3, employee.getPswrd());
			ps.setInt(4, employee.getEmpId());
			emplUpdated = ps.executeUpdate();

		} catch (SQLException a) {
			log.error(a);
		}

		return emplUpdated;
	}

	@Override
	public int deleteEmployeeById(int id, Connection con) {
		int emplDeleted = 0;

		String sql = "DELETE FROM EMPLOYEE WHERE EMP_ID = ?";

		try (PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			emplDeleted = ps.executeUpdate();

		} catch (SQLException a) {
			log.error(a);
		}

		return emplDeleted;
	}

	@Override
	public Employee getEmployeeByUserName(String userName) {
		String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ?";
		Employee e = null;
		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, userName);
			rs = ps.executeQuery();

			while (rs.next()) {
				e = new Employee();
				e.setEmpId(rs.getInt("EMP_ID"));
				e.setfName(rs.getString("FNAME"));
				e.setlName(rs.getString("LNAME"));
				e.setManId(rs.getInt("MAN_ID"));
				e.setPswrd(rs.getString("PSWRD"));
				e.setUserName(rs.getString("USERNAME"));
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
		return e;
	}

}

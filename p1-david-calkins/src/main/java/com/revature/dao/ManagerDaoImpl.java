package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.util.LogUtil;

public class ManagerDaoImpl implements ManagerDao {

	@Override
	public Manager getManagerById(long managerId, Connection con) throws SQLException {
		LogUtil.logDebug("Method Not Implemented");
		throw new UnsupportedOperationException("Method Not Implemented");
	}

	@Override
	public Manager getManagerByUsername(String username, Connection con) throws SQLException {
		LogUtil.logDebug("Method Not Implemented");
		throw new UnsupportedOperationException("Method Not Implemented");
	}

	@Override
	public Manager tryCastManager(Employee e, Connection con) throws SQLException {
		final String sql = "SELECT * FROM Manager M "
				+ "JOIN Employee E ON E.emp_id = M.emp_id "
				+ "WHERE M.manager_id = ?";
		
		Manager m = null;
		
		// A result set for iterating through the results
		ResultSet rs = null;
		
		ArrayList<Employee> employees = new ArrayList<>();
		
		// Attempt to create a statement and execute it. 
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, e.getId());
			
			rs = ps.executeQuery();
			
			// If there is a manager, go through it, getting the variables
			if (rs.next()) {
				m = new Manager();
				m.setId(e.getId());
				m.setName(e.getName());
				m.setUsername(e.getUsername());
				m.setPasswordHash(e.getPasswordHash());
//				System.out.println(m);
				// Read the row and add employees to the list
				do {
					employees.add(new Employee(
							rs.getLong("emp_id"),
							rs.getString("emp_name"),
							rs.getString("emp_username"),
							rs.getString("emp_password")
						));
				} while (rs.next());
				
				// Set the list to belong to the manager
				m.setEmployees(employees);
			}
		} finally {
			try {if (rs != null) rs.close();} catch(SQLException ex) {}
		}
		
		return m;
	}

	@Override
	public List<Manager> getAllManagers(Connection con) throws SQLException {
		LogUtil.logDebug("Method Not Implemented");
		throw new UnsupportedOperationException("Method Not Implemented");
	}

	
	/**
	 * Takes an sql statement, binding the values in the binds array, 
	 * and returns a list of all employees that matched the condition
	 * query. Any strings in {@code binds} will be bound to the query in the 
	 * same order that they are provided in. 
	 * where clause. 
	 * @param sql - The query to bind and execute. 
	 * @param binds - The values to bind to the query. 
	 * @return A list of all matching employees
	 * @throws SQLException - If there was a problem binding or executing the query. 
	 */
//	private List<Manager> getManager(String sql, String[] binds,
//										Connection con) throws SQLException 
//	{
//		// A manager with a list of employees
//
//		// A result set for iterating through the results
//		ResultSet rs = null;
//
//		// tODO: Temporary patch as PreparedStatements stopped working. 
//		for (int i=0; i<binds.length; i++) { 
//			sql = sql.replaceFirst("\\?", binds[i]);
//		}
//		
//		
//		// Attempt to create a statement and execute it. 
//		try (PreparedStatement ps = con.prepareStatement(sql)) {
//			// Bind the variables to the query
//			for (int i=0; i<binds.length; i++) {
//				// TODO: Temporary patch as prepared statements stopped working. 
////				ps.setString(i+1, binds[i]);
//			}
//			
//			System.out.println(sql);
//
//			// Keep in mind, this needs to be closed 
//			rs = ps.executeQuery();
//
//			// If there is a result, get it and return it. 
//			while (rs.next()) {
//				Manager e = new Manager();
//				e.setId(rs.getLong("emp_id"));
//				e.setName(rs.getString("emp_name"));
//				e.setUsername(rs.getString("emp_username"));
//				e.setPasswordHash(rs.getString("emp_password"));
//				employees.add(e);
//			}
//		} finally {
//			// Make an attempt at closing resources. Do nothing if closing fails
//			// Try to close the result set
//			try {if (rs != null)rs.close();} catch (SQLException ex) {}
//		}
//
//		return managers;
//	} // end of getManagers
	
	
	@Override
	public long insertManagerRelation(Manager man, Employee emp, Connection con) throws SQLException {
		LogUtil.logDebug("Method Not Implemented");
		throw new UnsupportedOperationException("Method Not Implemented");
	}
	
	
	

}

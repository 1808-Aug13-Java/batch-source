package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Manager;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public Employee getEmployeeById(long id, Connection con) throws SQLException {
		return getEmployee("emp_id", Long.toString(id), con);
	} // end of getEmployeeById
	
	@Override
	public Employee getEmployeeByUsername(String username, Connection con) throws SQLException {
		return getEmployee("emp_username", "'"+username+"'", con);
	} // end of getEmployeeByUsername
	

	/**
	 * 
	 * @param column
	 * @param value
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	private Employee getEmployee(String column, String value, Connection con) throws SQLException {
		Employee e = null;
		final String sql = "SELECT * FROM Employee WHERE ? = ?";
		final String[] binds = {column, value};
		
		// Get the list of matching employees. 
		List<Employee> emps = getEmployees(sql, binds, con);
		
		// If we got a result, return the employee. 
		if (!emps.isEmpty()) {
			e =  emps.get(0);
		}

		return e;
	} // end of getEmployee

	
	
	@Override
	/**
	 * Returns a list of all employees in the database. 
	 * @param con - The connection to use. 
	 * @return A list of all employees. 
	 * @throws SQLException - If there was a problem binding or executing the query. 
	 */
	public List<Employee> getAllEmployees(Connection con) throws SQLException {
		final String sql = "SELECT * FROM Employee WHERE ? = ?";
		final String[] binds = {"1", "1"};
		return getEmployees(sql, binds, con);
	} // end of getAllEmployees

	
	
	
	@Override
	public List<Employee> getEmployeesByManager(Manager man, Connection con) throws SQLException {
		final String sql = "SELECT * FROM Employee E"
				+ " JOIN Manager M ON M.emp_id = E.emp_id"
				+ " WHERE ? = ?";
		final String[] binds = {"manager_id", Long.toString(man.getId())};
		return getEmployees(sql, binds, con);
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
	private List<Employee> getEmployees(String sql, String[] binds,
										Connection con) throws SQLException 
	{
		// A list to return the employees. 
		ArrayList<Employee> employees = new ArrayList<>();

		// A result set for iterating through the results
		ResultSet rs = null;

		// tODO: Temporary patch as PreparedStatements stopped working. 
		for (int i=0; i<binds.length; i++) { 
			sql = sql.replaceFirst("\\?", binds[i]);
		}
		
		
		// Attempt to create a statement and execute it. 
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			// Bind the variables to the query
			for (int i=0; i<binds.length; i++) {
				// TODO: Temporary patch as prepared statements stopped working. 
//				ps.setString(i+1, binds[i]);
			}
			
//			System.out.println(sql);

			// Keep in mind, this needs to be closed 
			rs = ps.executeQuery();

			// If there is a result, get it and return it. 
			while (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getLong("emp_id"));
				e.setName(rs.getString("emp_name"));
				e.setUsername(rs.getString("emp_username"));
				e.setPasswordHash(rs.getString("emp_password"));
				employees.add(e);
			}
		} finally {
			// Make an attempt at closing resources. Do nothing if closing fails
			// Try to close the result set
			try {if (rs != null)rs.close();} catch (SQLException ex) {}
		}

		return employees;
	} // end of getEmployees
	
	
	
	@Override
	public long insertEmployee(Employee emp, Connection con) throws SQLException {
		final String sql = "INSERT INTO Employee (emp_id, emp_name, emp_username, emp_password)"
				+ " VALUES (?, ?, ?, ?)";
		// Used to hold the key from the generated object
		long key = -1;
		
		// A result set for getting the generated key
		ResultSet rs = null;
		
		// This is necessary as OracleDB doesn't properly return the generated 
		// key when using the Statement.RETURN_GENERATED_KEYS flag in a 
		// statement. 
		String[] keyName = {"emp_id"};
		
		// Attempt to create a statement and execute it. 
		try (PreparedStatement ps = con.prepareStatement(sql, keyName)) {
			// Bind the variables to the statement
			ps.setString(1, Long.toString(emp.getId()));
			ps.setString(2, emp.getName());
			ps.setString(3, emp.getUsername());
			ps.setString(4, emp.getPasswordHash());
			
			ps.executeUpdate();
			
			// Get the key that was generated from the insertion of the account
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getLong(1);
				
				// Also set the client's key
				emp.setId(key);
			}
			
		} finally {
			// Try to close the result set
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
		}
		
		return key;
	} // end of insertEmployee

	
	@Override
	public int updateEmployee(Employee emp, Connection con) throws SQLException {
		final String sql = "UPDATE Employee SET emp_name=?, "
				+ "emp_username=?, emp_password=? WHERE emp_id = ?";
		
		//The number of affected rows by this insertion. 
		int rowsAffected = 0;
		
		// Attempt to create a statement and execute it. 
		try (PreparedStatement ps = con.prepareStatement(sql)){
			// Bind the variables to the statement
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getUsername());
			ps.setString(3, emp.getPasswordHash());
			ps.setLong(4, emp.getId());
			
			rowsAffected = ps.executeUpdate();
		}
		
		return rowsAffected;
	}


}

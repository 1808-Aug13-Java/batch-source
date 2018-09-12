package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;

public class ReimbursementDaoImpl implements ReimbursementDao{
	
	
	
	@Override
	public Reimbursement getById(long id, Connection con) throws SQLException {
		String[] binds = {"rem_id", Long.toString(id)};
		String[] conditions = {"="};
		
		List<Reimbursement> reims = getReimbursements(binds, conditions, con);
		
		if (reims.isEmpty()) {
			return null;
		}
		
		return reims.get(0);
	}

	@Override
	public List<Reimbursement> getAll(Connection con) throws SQLException {
		String[] binds = {"1", "1"};
		String[] conditions = {"="};
		return getReimbursements(binds, conditions, con);
	}

	@Override
	public List<Reimbursement> getAllPending(Connection con) throws SQLException {
		String[] binds = {"status_string", "'Pending'"};
		String[] conditions = {"="};
		return getReimbursements(binds, conditions, con);
	}

	@Override
	public List<Reimbursement> getAllResolved(Connection con) throws SQLException {
		String[] binds = {"status_string", "'Pending'"};
		String[] conditions = {"!="};
		return getReimbursements(binds, conditions, con);
	}
	
	
	@Override
	public List<Reimbursement> getByRequester(Employee emp, Connection con) throws SQLException {
		String[] binds = {"requester_id", Long.toString(emp.getId())};
		String[] conditions = {"="};
		return getReimbursements(binds, conditions, con);
	}
	

	@Override
	public List<Reimbursement> getPendingByRequester(Employee emp, Connection con) throws SQLException {
		String[] binds = {"requester_id", Long.toString(emp.getId()), 
				"status_string", "'Pending'"};
		String[] conditions = {"=", "="};
		return getReimbursements(binds, conditions, con);
	}

	@Override
	public List<Reimbursement> getResolvedByRequester(Employee emp, Connection con) throws SQLException {
		String[] binds = {"requester_id", Long.toString(emp.getId()), 
				"status_string", "'Pending'"};
		String[] conditions = {"=", "!="};
		return getReimbursements(binds, conditions, con);
	}
	
	
	
	
	
	private List<Reimbursement> getReimbursements(String[] binds, String[] conditions,
											Connection con) throws SQLException 
	{
		String sql = "SELECT "
				+ "REM_ID, AMOUNT, SUBMIT_DATE, DESCR, RESOLVED_BY, REASON, RESOLVE_DATE, STATUS_STRING, "
				+ "E.EMP_ID as REQUESTER_ID, E.EMP_NAME as REQUESTER_NAME, E.EMP_USERNAME as REQUESTER_USERNAME, "
				+ "M.EMP_ID as RESOLVER_ID, M.EMP_NAME as RESOLVER_NAME, M.EMP_USERNAME as RESOLVER_USERNAME "
				+ "FROM Reimbursement R "
				+ "JOIN Reimbursement_status RS ON R.status = RS.status_number "
				+ "LEFT JOIN Employee E ON E.emp_id = R.requester_id "
				+ "LEFT JOIN Employee M ON M.emp_id = R.resolved_by "
				+ "WHERE ? ~ ?";
		// A list to return the reimbursements. 
		ArrayList<Reimbursement> reimbursements = new ArrayList<>();

		// A result set for iterating through the results
		ResultSet rs = null;
		
		// Used for processing possible null inputs
		Object tempObject = null;
		
		
		// If here are more than two binds, add more and clauses. 
		if (binds.length > 2) {
			StringBuilder sb = new StringBuilder(sql);
			for (int i=2; i<binds.length; i+=2) {
				sb.append(" AND ? ~ ?");
			}
			sql = sb.toString();
		}
		
		// Replace the tildas with conditional. 
		for (String condition : conditions) {
			sql = sql.replaceFirst("~", condition);
		}
		
		// TODO: Temporary Patch as Can't Test equality between a Bound String
		// and a Bound Number. Sigh
		for (int i=0; i<binds.length; i++) {
			sql = sql.replaceFirst("\\?", binds[i]);
		}
		
		// Attempt to create a statement and execute it. 
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			// Bind the variables to the query
//			for (int i=0; i<columns.length; i++) {
//				sql = sql.replaceFirst("\\?", columns[i]);
////				ps.setObject(i*2+1, columns[i]);
//				ps.setObject(i+1, values[i]);
//			}
			
			

			// Keep in mind, this needs to be closed 
			rs = ps.executeQuery();

			
			// TODO: temp var for debugging
//			int columns = rs.getMetaData().getColumnCount();
			//TODO: for debugging
//			for (int i=1; i <= columns; i++) {
//				System.out.print(rs.getMetaData().getColumnLabel(i) + ", ");
//			}
//			System.out.println();
			
			// If there is a result, get it and return it. 
			while (rs.next()) {
				Reimbursement reim = new Reimbursement();
				
				
				// Get Non-null values
				reim.setId(rs.getLong("rem_id"));
				reim.setStatus(rs.getString("status_string"));
				reim.setAmount(rs.getDouble("amount"));
				
				// Set possibly null values
				reim.setDescription(rs.getString("descr"));
				reim.setReason(rs.getString("reason"));
				
				// Get the submit date and resolve date
				reim.setSubmitDate(rs.getDate("submit_date"));
				reim.setResolveDate(rs.getDate("resolve_date"));
				
				// Set the employee making the request. Not Null.
				reim.setRequester(new Employee(
							rs.getLong("requester_id"),
							rs.getString("requester_name"),
							rs.getString("requester_username"),
							null // No need for the user's hashed password
						));
				
				// Set the manager who resolved the request. Possibly null. 
				tempObject = rs.getString("resolver_username");
				if (tempObject != null) {
					reim.setResolvedBy(new Manager(
							rs.getLong("resolver_id"),
							rs.getString("resolver_name"),
							rs.getString("resolver_username"),
							null, // No need for the user's hashed password
							new LinkedList<Employee>()
						));
				}
				
				reimbursements.add(reim);
			}
		} finally {
			// Make an attempt at closing resources. Do nothing if closing fails
			// Try to close the result set
			try {if (rs != null)rs.close();} catch (SQLException ex) {}
		}

		return reimbursements;
	}
	
	
	
	
	
	@Override
	public long createReimRequest(Reimbursement reim, Connection con) throws SQLException {
		final String sql = "INSERT INTO Reimbursement "
				+ "(rem_id, requester_id, status, amount, submit_date, descr, "
				+ "resolved_by, reason, resolve_date)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		// Used to hold the key from the generated object
		long key = -1;
		
		// A result set for getting the generated key
		ResultSet rs = null;
		
		// This is necessary as OracleDB doesn't properly return the generated 
		// key when using the Statement.RETURN_GENERATED_KEYS flag in a 
		// statement. 
		String[] keyName = {"rem_id"};
		
		// Attempt to create a statement and execute it. 
		try (PreparedStatement ps = con.prepareStatement(sql, keyName)) {
			// Bind the variables to the statement
			ps.setLong(1, reim.getId());
			ps.setLong(2, reim.getRequester().getId());
			// Extract the status from the string
			//TODO: Temporarily hardcoded
			long status = 1;
			switch (reim.getStatus()) {
			case "Pending": status = 1; break;
			case "Approved": status = 2; break;
			case "Denied": status = 3; break;
			}
			ps.setLong(3, status);
			ps.setDouble(4, reim.getAmount());
			ps.setDate(5, (reim.getSubmitDate()==null? null :
							new java.sql.Date(reim.getSubmitDate().getTime())));
			ps.setString(6, reim.getDescription());
			// Handle null variable
			if (reim.getResolvedBy()==null) {
				ps.setNull(7, java.sql.Types.INTEGER);
			} else {
				ps.setLong(7, reim.getResolvedBy().getId());
			}
			
			ps.setString(8, reim.getReason());
			ps.setDate(9,  reim.getResolveDate()==null? null : 
							new java.sql.Date(reim.getResolveDate().getTime()));
			
			ps.executeUpdate();
			
			// Get the key that was generated from the insertion of the account
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				key = rs.getLong(1);
				
				// Also set the client's key
				reim.setId(key);
			}
			
		} finally {
			// Try to close the result set
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
		}
		
		return key;
	}

	
	
	
	@Override
	public int updateReimRequest(Reimbursement reim, Connection con) throws SQLException {
		final String sql = "UPDATE Reimbursement SET requester_id=?, "
				+ "status=?, amount=?, submit_date=?, descr=?, resolved_by=?, "
				+ "reason=?, resolve_date=? WHERE rem_id = ?";
		
		//The number of affected rows by this insertion. 
		int rowsAffected = 0;
		
		// Attempt to create a statement and execute it. 
		try (PreparedStatement ps = con.prepareStatement(sql)){
			ps.setLong(1, reim.getRequester().getId());
			// Extract the status from the string
			//TODO: Temporarily hardcoded
			long status = 1;
			switch (reim.getStatus()) {
			case "Pending": status = 1; break;
			case "Approved": status = 2; break;
			case "Denied": status = 3; break;
			}
			ps.setLong(2, status);
			ps.setDouble(3, reim.getAmount());
			ps.setDate(4, (reim.getSubmitDate()==null? null :
							new java.sql.Date(reim.getSubmitDate().getTime())));
			ps.setString(5, reim.getDescription());
			// Handle null variable
			if (reim.getResolvedBy()==null) {
				ps.setNull(6, java.sql.Types.INTEGER);
			} else {
				ps.setLong(6, reim.getResolvedBy().getId());
			}
			
			ps.setString(7, reim.getReason());
			ps.setDate(8,  reim.getResolveDate()==null? null : 
							new java.sql.Date(reim.getResolveDate().getTime()));
			
			ps.setLong(9, reim.getId());
			
			rowsAffected = ps.executeUpdate();
		}
		
		return rowsAffected;
	}

	

}

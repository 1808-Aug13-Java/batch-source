package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.model.Transaction;
import com.revature.util.ProjectOneConnection;

public class TransactionDaoImpl implements TransactionDao {

	@Override
	public List<Transaction> getTransactions() {
		List<Transaction> transactions = new ArrayList<>();
		
		
		String sql = "SELECT * FROM TRANS_ACTION";
		
		try(Connection con = ProjectOneConnection.getConnection(); 
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql)){
		
			while(rs.next())
			{
				Transaction t = new Transaction();
				
				int id = rs.getInt("TRANSACTION_ID");
				t.setId(id);
				
				Date date = rs.getDate("TRANS_DATE");
				t.setDate(date);
				
				int creator = rs.getInt("EMP_ID");
				t.setCreator(creator);
				
				String status = rs.getString("STATUS");
				t.setApproved(status);
				
				String approverName = rs.getString("APPROVER");
				t.setApproverName(approverName);
				
				float amount = rs.getFloat("AMOUNT");
				t.setAmount(amount);
				
				String reason = rs.getString("REASON");
				t.setReason(reason);
				
				transactions.add(t);
			}
		}
		catch(IOException | SQLException e){
			e.printStackTrace();;
		};
		
		return transactions;
	}

	@Override
	public Transaction getTransactionById(int id) {
		Transaction trans = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM TRANS_ACTION WHERE TRANSACTION_ID = ?";
		try(Connection con = ProjectOneConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Date date = rs.getDate("TRANS_DATE");
				int creator = rs.getInt("EMP_ID");
				String status = rs.getString("STATUS");
				String approverName = rs.getString("APPROVER");
				float amount = rs.getFloat("AMOUNT");
				String reason = rs.getString("REASON");
				trans = new Transaction(id, date, creator, status, approverName, amount, reason);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return trans;
	}

	@Override
	public int createTransaction(Transaction transaction) {
		String sql = "INSERT INTO TRANS_ACTION (TRANSACTION_ID, TRANS_DATE, EMP_ID, STATUS, APPROVER, AMOUNT, REASON) "
				+ "values (1, ?, ?, ?, ?, ?, ?)";
		ResultSet transactionsCreated = null;
		
		try(Connection con = ProjectOneConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			con.setAutoCommit(false);
			ps.setDate(1, transaction.getDate());
			ps.setInt(2, transaction.getCreator());
			ps.setString(3, transaction.getApproved());
			ps.setString(4, transaction.getApproverName());
			ps.setFloat(5, transaction.getAmount());
			ps.setString(6,  transaction.getReason());
			transactionsCreated = ps.executeQuery();
			con.commit();
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if(transactionsCreated != null)
			{
				try {
					transactionsCreated.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return 1;
	}

	@Override
	public int deleteTransactionById(int id) {
		return 0;
	}

	@Override
	public int updateTransaction(int id, String newStatus, String name) {
		
		String sql = "UPDATE TRANS_ACTION " + "SET STATUS = ?, APPROVER = ?" + 
				" WHERE TRANSACTION_ID =  ?";
				
				try(Connection con = ProjectOneConnection.getConnection();
						PreparedStatement ps =  con.prepareStatement(sql)){
				
					con.setAutoCommit(false);
					ps.setString(1, newStatus);
					ps.setString(2, name);
					ps.setInt(3, id);
					ps.executeQuery();
					con.commit();
					
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}
				
				return 1;
	}

	@Override
	public Transaction getTransactionByEmployee(int id) {
		Transaction trans = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM TRANS_ACTION WHERE TRANSACTION_ID = ?";
		try(Connection con = ProjectOneConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Date date = rs.getDate("TRANS_DATE");
				int creator = rs.getInt("EMP_ID");
				String status = rs.getString("STATUS");
				String approverName = rs.getString("APPROVER");
				float amount = rs.getFloat("AMOUNT");
				String reason = rs.getString("REASON");
				
				trans = new Transaction(id, date, creator, status, approverName, amount, reason);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return trans;
	}

	@Override
	public List<Transaction> getPending() {
List<Transaction> transactions = new ArrayList<>();
		
		String sql = "SELECT * FROM TRANS_ACTION WHERE STATUS = 'PENDING'";
		
		try(Connection con = ProjectOneConnection.getConnection(); 
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql)){
		
			while(rs.next())
			{
				Transaction t = new Transaction();
				
				int id = rs.getInt("TRANSACTION_ID");
				t.setId(id);
				
				Date date = rs.getDate("TRANS_DATE");
				t.setDate(date);
				
				int creator = rs.getInt("EMP_ID");
				t.setCreator(creator);
				
				String status = rs.getString("STATUS");
				t.setApproved(status);
				
				String approverName = rs.getString("APPROVER");
				t.setApproverName(approverName);
				
				float amount = rs.getFloat("AMOUNT");
				t.setAmount(amount);
				
				String reason = rs.getString("REASON");
				t.setReason(reason);
				
				transactions.add(t);
			}
		}
		catch(IOException | SQLException e){
			e.printStackTrace();
		}
		
		return transactions;		
	}

	@Override
	public List<Transaction> getResolved() {
		
		List<Transaction> transactions = new ArrayList<>();
		
		String sql = "SELECT * FROM TRANS_ACTION WHERE STATUS != 'PENDING'";
		
		try(Connection con = ProjectOneConnection.getConnection(); 
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql)){
		
			while(rs.next())
			{
				Transaction t = new Transaction();
				
				int id = rs.getInt("TRANSACTION_ID");
				t.setId(id);
				
				Date date = rs.getDate("TRANS_DATE");
				t.setDate(date);
				
				int creator = rs.getInt("EMP_ID");
				t.setCreator(creator);
				
				String status = rs.getString("STATUS");
				t.setApproved(status);
				
				String approverName = rs.getString("APPROVER");
				t.setApproverName(approverName);
				
				float amount = rs.getFloat("AMOUNT");
				t.setAmount(amount);
				
				String reason = rs.getString("REASON");
				t.setReason(reason);
				
				transactions.add(t);
			}
		}
		catch(IOException | SQLException e){
			e.printStackTrace();
		}
		
		return transactions;		
	}

	@Override
	public List<Transaction> getTransactionsOfEmployee(int employeeID) {
		List<Transaction> transactions = new ArrayList<>();
		
		String sql = "SELECT * FROM TRANS_ACTION WHERE EMP_ID = ?";
		
		try(Connection con = ProjectOneConnection.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, employeeID);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next())
			{
				Transaction t = new Transaction();
				
				int id = rs.getInt("TRANSACTION_ID");
				t.setId(id);
				
				Date date = rs.getDate("TRANS_DATE");
				t.setDate(date);
				
				int creator = rs.getInt("EMP_ID");
				t.setCreator(creator);
				
				String status = rs.getString("STATUS");
				t.setApproved(status);
				
				String approverName = rs.getString("APPROVER");
				t.setApproverName(approverName);
				
				float amount = rs.getFloat("AMOUNT");
				t.setAmount(amount);
				
				String reason = rs.getString("REASON");
				t.setReason(reason);
				
				transactions.add(t);
			}
		}
		catch(IOException | SQLException e){
			e.printStackTrace();
		}
		
		return transactions;		
	}

	@Override
	public List<Transaction> getPendingOfEmployee(int employeeId) {
		
		List<Transaction> transactions = new ArrayList<>();
		String sql = "SELECT * FROM TRANS_ACTION WHERE EMP_ID = ? AND STATUS = ?";
		
		try(Connection con = ProjectOneConnection.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, employeeId);
			ps.setString(2, "PENDING");
			ResultSet rs = ps.executeQuery();
		
			while(rs.next())
			{
				Transaction t = new Transaction();
				
				int id = rs.getInt("TRANSACTION_ID");
				t.setId(id);
				
				Date date = rs.getDate("TRANS_DATE");
				t.setDate(date);
				
				int creator = rs.getInt("EMP_ID");
				t.setCreator(creator);
				
				String status = rs.getString("STATUS");
				t.setApproved(status);
				
				String approverName = rs.getString("APPROVER");
				t.setApproverName(approverName);
				
				float amount = rs.getFloat("AMOUNT");
				t.setAmount(amount);
				
				String reason = rs.getString("REASON");
				t.setReason(reason);
				
				transactions.add(t);
			}
		}
		catch(IOException | SQLException e){
			e.printStackTrace();
		}
		
		return transactions;		
	}

	@Override
	public List<Transaction> getResolvedOfEmployee(int employeeId) {
		
		List<Transaction> transactions = new ArrayList<>();		
		String sql = "SELECT * FROM TRANS_ACTION WHERE EMP_ID = ? AND STATUS != 'PENDING'";
		
		try(Connection con = ProjectOneConnection.getConnection(); 
			PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next())
			{
				Transaction t = new Transaction();
				
				int id = rs.getInt("TRANSACTION_ID");
				t.setId(id);
				
				Date date = rs.getDate("TRANS_DATE");
				t.setDate(date);
				
				int creator = rs.getInt("EMP_ID");
				t.setCreator(creator);
				
				String status = rs.getString("STATUS");
				t.setApproved(status);
				
				String approverName = rs.getString("APPROVER");
				t.setApproverName(approverName);
				
				float amount = rs.getFloat("AMOUNT");
				t.setAmount(amount);
				
				String reason = rs.getString("REASON");
				t.setReason(reason);
				
				transactions.add(t);
			}
		}
		catch(IOException | SQLException e){
			e.printStackTrace();
		}
		
		return transactions;		
	}
}

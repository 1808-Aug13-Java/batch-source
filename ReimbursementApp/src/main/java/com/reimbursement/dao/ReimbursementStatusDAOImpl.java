package com.reimbursement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.reimbursement.model.ReimbursementStatus;

public class ReimbursementStatusDAOImpl implements ReimbursementStatusDAO {

	
	public class ReimbursementStatusDAOImplementation implements ReimbursementStatusDAO {

	    private Connection connection = null;

	    public ReimbursementStatusDAOImplementation(Connection connection) {
	        this.connection = connection;
	    }

	    @Override
	    public ReimbursementStatus getReimbursementStatus(int statusId) {
	        String sql = "SELECT REIMB_STATUS FROM ERS_REIMBURSEMENT_STATUS WHERE REIMB_STATUS_ID = ?";
	        PreparedStatement statement;
	        String status = "";
	        ResultSet resultSet;
	        try {
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1,statusId);
	            resultSet =  statement.executeQuery();
	            resultSet.next();
	            status = resultSet.getString("REIMB_STATUS");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return new ReimbursementStatus(statusId, status);

	    }

	    @Override
	    public List<ReimbursementStatus> getReimbursementStatus() {
	        String sql = "SELECT REIMB_STATUS_ID, REIMB_STATUS FROM ERS_REIMBURSEMENT_STATUS";
	        List<ReimbursementStatus> values = new ArrayList<>();
	        PreparedStatement statement;
	        ResultSet resultSet;
	        try {
	            statement = connection.prepareStatement(sql);
	            resultSet = statement.executeQuery();
	            while(resultSet.next()) {
	                values.add(new ReimbursementStatus(resultSet.getInt("REIMB_STATUS_ID"), resultSet.getString("REIMB_STATUS")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return values;
	    }
	}

	@Override
	public ReimbursementStatus getReimbursementStatus(int statusId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReimbursementStatus> getReimbursementStatus() {
		// TODO Auto-generated method stub
		return null;
	}


}

package com.reimbursement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import com.reimbursement.model.Reimbursement;
import com.reimbursement.model.ReimbursementStatus;
import com.reimbursement.model.User;

public class ReimbursementDAOImpl implements ReimbursementDAO {

	private Connection connection = null;


    /**
     * Instantiates a new Reimbursement dao implementation.
     *
     * @param connection the connection
     */
    public ReimbursementDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Reimbursement reimbursement) {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO ERS_REIMBURSEMENT"
                + " (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_STATUS_ID) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, reimbursement.getAmount());
            statement.setTimestamp(2, reimbursement.getSubmitted());
            statement.setString(3, reimbursement.getDescription());
            statement.setInt(4, reimbursement.getAuthor().getUserId());
            statement.setInt(5, reimbursement.getStatusId());
            statement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            //System.out.println(" SQL Exception: The reimbursement couldn't be inserted");
            e.printStackTrace();
        }
    }

    @Override
    public List<Reimbursement> getReimbursements() throws AuthenticationException {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM ERS_REIMBURSEMENT";
        ResultSet resultSet;
        ArrayList<Reimbursement> list = new ArrayList<>();
        PreparedStatement statement;
        UserDAO userDAO = UserDAOConn.getInstance(connection);
        ReimbursementStatusDAO statusDAO = ReimbursementStatusDAOConn.getInstance(connection);
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Reimbursement(resultSet.getInt("REIMB_ID"),
                        resultSet.getDouble("REIMB_AMOUNT"),
                        resultSet.getTimestamp("REIMB_SUBMITTED"),
                        resultSet.getTimestamp("REIMB_RESOLVED"),
                        resultSet.getString("REIMB_DESCRIPTION"),
                        userDAO.getByUserId(resultSet.getInt("REIMB_AUTHOR")),
                        resultSet.getInt("REIMB_RESOLVER") != 0 ? userDAO.getByUserId(resultSet.getInt("REIMB_RESOLVER")) : null,
                        statusDAO.getReimbursementStatus(resultSet.getInt("REIMB_STATUS_ID"))));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Something went wrong when getting the list of reimbursements");
        }
        return list;
    }

    @Override
    public List<Reimbursement> getReimbursements(String username) throws AuthenticationException {
        // TODO Auto-generated method stub
        UserDAO userDAO = UserDAOConn.getInstance(connection);
        ReimbursementStatusDAO statusDAO = ReimbursementStatusDAOConn.getInstance(connection);
        String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR = ?";
        User user = userDAO.getByUsername(username);
        PreparedStatement statement;
        ResultSet resultSet;
        List<Reimbursement> reimbursements = new ArrayList<>();
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getUserId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reimbursements.add(new Reimbursement(resultSet.getInt("REIMB_ID"),
                        resultSet.getDouble("REIMB_AMOUNT"),
                        resultSet.getTimestamp("REIMB_SUBMITTED"),
                        resultSet.getTimestamp("REIMB_RESOLVED"),
                        resultSet.getString("REIMB_DESCRIPTION"),
                        userDAO.getByUserId(resultSet.getInt("REIMB_AUTHOR")),
                        resultSet.getInt("REIMB_RESOLVER") != 0 ? userDAO.getByUserId(resultSet.getInt("REIMB_RESOLVER")) : null,
                        statusDAO.getReimbursementStatus(resultSet.getInt("REIMB_STATUS_ID"))));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("SQL Exception: The reimbursements couldn't be retrieved");
        }
        return reimbursements;
    }

    @Override
    public Reimbursement createNewReimbursement(String username, double amount, String description, ReimbursementStatus status) throws AuthenticationException {
        UserDAO userDAO = UserDAOConn.getInstance(connection);
        User user = userDAO.getByUsername(username);
        return new Reimbursement(user.getUserId(), amount, null, null, description, user, status, status);
    }

    @Override
    public void changeStatus(int reimbursementId, int reimbursementStatusId, String username) throws AuthenticationException {
        String sql = "UPDATE ERS_REIMBURSEMENT SET REIMB_STATUS_ID = ?, REIMB_RESOLVER = ?, REIMB_RESOLVED = ? WHERE REIMB_ID = ?";
        UserDAO userDAO = UserDAOConn.getInstance(connection);
        User user = userDAO.getByUsername(username);
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, reimbursementStatusId);
            statement.setInt(2, user.getUserId());
            statement.setTimestamp(3, new Timestamp(0));
            statement.setInt(4, reimbursementId);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
	
	


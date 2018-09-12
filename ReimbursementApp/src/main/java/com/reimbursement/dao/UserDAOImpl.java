package com.reimbursement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.AuthenticationException;

import com.reimbursement.model.User;

public class UserDAOImpl implements UserDAO {
    private Connection connection = null;

    public void UserDAOImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getByUsername(String userName) throws AuthenticationException {
        User user = new User();
        ResultSet resultSet;
        PreparedStatement statement;
        String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ?";
        RoleDAO dao = RoleDAOConn.getInstance(connection);
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new AuthenticationException("The user does not exist");
            }
            user.setEmail(resultSet.getString("USER_EMAIL"));
            user.setFirstName(resultSet.getString("USER_FIRST_NAME"));
            user.setLastName(resultSet.getString("USER_LAST_NAME"));
            user.setPassword(resultSet.getString("ERS_PASSWORD"));
            user.setRole(dao.getRole(resultSet.getInt("USER_ROLE_ID")));
            user.setUserId(resultSet.getInt("ERS_USERS_ID"));
            user.setUsername(resultSet.getString("ERS_USERNAME"));
        } catch (SQLException e) {
            System.out.println("The username couldn't be accessed");
        }
        return user;
    }

    @Override
    public User getByUserId(int userId) throws AuthenticationException {
        User user = new User();
        ResultSet resultSet;
        PreparedStatement statement;
        String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERS_ID = ?";
        RoleDAO dao = RoleDAOConn.getInstance(connection);
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new AuthenticationException("The user does not exist");
            }
            user.setEmail(resultSet.getString("USER_EMAIL"));
            user.setFirstName(resultSet.getString("USER_FIRST_NAME"));
            user.setLastName(resultSet.getString("USER_LAST_NAME"));
            user.setPassword(resultSet.getString("ERS_PASSWORD"));
            user.setRole(dao.getRole(resultSet.getInt("USER_ROLE_ID")));
            user.setUserId(resultSet.getInt("ERS_USERS_ID"));
            user.setUsername(resultSet.getString("ERS_USERNAME"));
        } catch (SQLException e) {
            System.out.println("The username couldn't be accessed");
        }
        return user;
    }
}

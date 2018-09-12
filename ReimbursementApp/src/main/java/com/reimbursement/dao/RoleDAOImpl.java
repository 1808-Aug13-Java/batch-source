package com.reimbursement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.reimbursement.model.Role;

public class RoleDAOImpl implements RoleDAO{

	    Connection connection = null;

	    @Override
	    public Role getRole(int roleID) {
	        String sql = "SELECT USER_ROLE FROM ERS_USER_ROLES WHERE ERS_USER_ROLE_ID = ?";
	        PreparedStatement statement;
	        ResultSet resultSet;
	        String userRole = "";
	        try {
	            statement = connection.prepareStatement(sql);
	            statement.setInt(1, roleID);
	            resultSet = statement.executeQuery();
	            resultSet.next();
	            userRole = resultSet.getString("USER_ROLE");
	        } catch (SQLException e) {
	            System.out.println("Something went wrong when getting the user role");
	        }
	        return new Role(roleID, userRole);
	    }
	    public void RoleDAOImpl(Connection connection) {
	        this.connection = connection;
	    }
	}


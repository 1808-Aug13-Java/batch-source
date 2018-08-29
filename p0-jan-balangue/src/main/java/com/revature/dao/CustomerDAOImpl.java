package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.Customer;
import com.revature.util.ConnectionUtil;

import org.apache.log4j.*;

public class CustomerDAOImpl implements CustomerDAO {

	private static Logger log = Logger.getRootLogger();
	
	@Override
	public List<Customer> getCustomers() {
		
		List<Customer> customerList = new ArrayList<>();
		
		String sql = "SELECT * FROM CUSTOMER";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			while(rs.next()) {
				Customer c = null;
				String customerName = rs.getString("CUSTNAME");
				String password = rs.getString("CUSTPASSWORD");
				c = new Customer(customerName, password);
				customerList.add(c);
			}
		} catch (IOException|SQLException e) {
			log.error(e);
		}
		return customerList;
	}

	@Override
	public Customer getCustomerById(int id) {
		Customer c = new Customer();
		String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMERID = ?";
		
		// Connection object
		Connection con;
		// An object for using a Prepared SQL Statement
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			// Open the connection to the 
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			// Keep in mind, this needs to be closed 
			rs = ps.executeQuery();
			
			if (rs.next()) {
				c.setCustomerName(rs.getString("CUSTNAME"));
				c.setPassword(rs.getString("CUSTPASSWORD"));
			}
		} catch (IOException | SQLException e) {
			log.error(e);
		} finally {
			try {if (rs!=null) rs.close();} catch(SQLException e) {}
			try {if (ps!=null) ps.close();} catch(SQLException e) {}
		}	
		return c;
	}

	@Override
	public int createCustomer(Customer customer) {
		
		String sql = "INSERT INTO CUSTOMER (CUSTNAME, CUSTPASSWORD) values (?, ?)";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		int customersUpdated = 0;
		
		try {
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, customer.getCustomerName());
			ps.setString(2, customer.getPassword());
			 
			customersUpdated = ps.executeUpdate();
		} catch (IOException | SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {if (ps != null) ps.close();} catch(SQLException e) {}
		}
		return customersUpdated;
	}

	@Override
	public int updateCustomer(Customer customer) {
		return 0;
	}

	@Override
	public int deleteCustomerById(int id) {
		int rowsDeleted = 0;
		
		String sql = "DELETE FROM CUSTOMER WHERE CUSTOMERID = ?";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConnectionUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			rowsDeleted = ps.executeUpdate();
		} catch (SQLException | IOException e) {
			log.error(e);
		} finally {
			try {if (ps != null) ps.close();} catch(SQLException e) {}
		}
		
		return rowsDeleted;
	}
}

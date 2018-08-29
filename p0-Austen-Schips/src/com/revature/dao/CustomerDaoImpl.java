package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.model.Customers;
import com.revature.util.ConnectionUtil;

public class CustomerDaoImpl {

	private static Logger log = Logger.getRootLogger();
	
	public Customers getCustomerByUserName() {
		Customers d = new Customers();
		String sql = "SELECT * FROM CUSTOMERS WHERE USERNAME = ? AND USER_PASSWORD = ?";

		ResultSet rs = null;

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){

			Scanner scan = new Scanner(System.in);
			
			log.info("Please validate your username: ");
			String my_username = scan.nextLine();
			
			ps.setString(1, my_username);
			
			
			log.info("Please validate your password: ");
			String my_password = scan.nextLine();
			
			ps.setString(2, my_password);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int customerId = rs.getInt("CUSTOMERSID");
				String firstName = rs.getString("FIRSTNAME");
				String lastName = rs.getString("LASTNAME");
				String userName1 = rs.getString("USERNAME");
				String userPassword1 = rs.getString("USER_PASSWORD");
				float balance = rs.getFloat("BALANCE");
				d = new Customers(customerId, firstName, lastName, userName1, userPassword1, balance);
				log.info(d.toString());
			}
		} catch (IOException|SQLException e) {
			log.error(e.getMessage());
		} finally {
			if (rs!=null) {
				try {
					log.info("Credentials successfully verified");
					rs.close();
			
				} catch (SQLException e) {
					log.error(e.getMessage());
				}
			}
		}

		return d;
	
}
	
	public int createCustomer() {
		int customersCreated = 0;
		String sql = "INSERT INTO CUSTOMERS (FIRSTNAME, LASTNAME, USERNAME, USER_PASSWORD, BALANCE) VALUES (?,?,?,?,?)";
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			
			Scanner scan = new Scanner(System.in);
			
			log.info("Please enter first name: ");
			String first_text = scan.nextLine();
			ps.setString(1, first_text);
			
			log.info("Please enter last name: ");
			String last_text = scan.nextLine();
			ps.setString(2, last_text);
			
			log.info("Please create username: ");
	        
	        String user_text = scan.nextLine();
			ps.setString(3, user_text);
			
			log.info("Please create password: ");
	        String password_text = scan.nextLine();
			ps.setString(4, password_text);
			
			log.info("Please enter initial balance: ");
	        float balance_text = scan.nextFloat();
			ps.setFloat(5, balance_text);
			
			customersCreated = ps.executeUpdate();
			
		} catch (SQLException|IOException e) {
			log.error(e);
		} 
		return customersCreated;
	}
	public int increaseBalance(String user, float increaseAmount) {
		
		String sql = "{call INCREASE_BALANCE(?,?)}";
		
		try( Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setString(1, user);
			cs.setFloat(2, increaseAmount);
			cs.execute();
		log.info("Transaction successful");
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		return 0;
	}
	
	public List<Customers> getCustomers(){
List<Customers> customers = new ArrayList<>();
		
		String sql = "SELECT * FROM DEPARTMENT";
		
		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)){
			
			while (rs.next()) {
				int custId = rs.getInt("CUSTOMERSID");
				String firstName = rs.getString("FIRSTNAME");
				String lastName = rs.getString("LASTNAME");
				String username = rs.getString("USERNAME");
				String password = rs.getString("USER_PASSWORD");
				float budget = rs.getFloat("BALANCE");
				
				customers.add(new Customers(custId, firstName, lastName, username, password, budget));
			}
			
			
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		
		return customers;
	}
	
}




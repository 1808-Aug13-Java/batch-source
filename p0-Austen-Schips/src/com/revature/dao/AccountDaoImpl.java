package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.revature.model.Accounts;
import com.revature.model.Customers;
import com.revature.util.ConnectionUtil;
import org.apache.log4j.Logger;
public class AccountDaoImpl {
	private static Logger log = Logger.getRootLogger();
	public void showBalance(Connection con)
	         throws ClassNotFoundException, SQLException {

	         PreparedStatement preparedStatement = con.prepareStatement("SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNTID = ?");
	         ResultSet resultSet =
	            preparedStatement.executeQuery();
	        
	}
	public int increaseBalance(int accountId, float increaseAmount) {
		
		String sql = "{call INCREASE_BALANCE(?,?)}";
		
		try( Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setInt(1, accountId);
			cs.setFloat(2, increaseAmount);
			cs.execute();
		log.info("Successfully Deposited");
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		return 0;
	}
	

	public void decreaseBalance(int accountId, float decreaseAmount) {
	
String sql = "{call INCREASE_BALANCE(?,?)}";
		
		try( Connection con = ConnectionUtil.getConnection();
				CallableStatement cs = con.prepareCall(sql)){
			
			cs.setInt(1, accountId);
			cs.setFloat(2, decreaseAmount);
			cs.execute();
		log.info("Withdrawal successful");
		} catch (SQLException|IOException e) {
			log.error(e.getMessage());
		} 
		
   
}
	
	public Accounts getAccountsById() {
			Accounts a = new Accounts();
			String sql = "SELECT * FROM ACCOUNTS WHERE ACCOUNTID = ?";

			ResultSet rs = null;

			try (Connection con = ConnectionUtil.getConnection();
					PreparedStatement ps = con.prepareStatement(sql);){

				Scanner scan = new Scanner(System.in);
				
				System.out.println("Please enter your accountId: ");
				String my_username = scan.nextLine();
				
				ps.setString(1, my_username);
				
				rs = ps.executeQuery();
				
				while (rs.next()) {
					int accountId = rs.getInt("ACCOUNTID");
					Float balance = rs.getFloat("BALNCE");
					
					a = new Accounts(accountId, balance);
					System.out.println(a.toString());
				}
			} catch (IOException|SQLException e) {
				//log.error(e.getMessage());
				e.printStackTrace();
				System.out.println("Error?");
			} finally {
				if (rs!=null) {
					try {
						System.out.println("Account ID successfully entered");
						rs.close();
				
					} catch (SQLException e) {
						//log.error(e.getMessage());
					}
				}
			}

			return a;
		
	}
	
	
	
	
	public int createAccounts() {
		int accountsCreated = 0;
		String sql = "INSERT INTO ACCOUNTS (BALANCE, CUSTOMERSID) VALUES (?)";
		
		
		
		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			
			Scanner scan = new Scanner(System.in);
			double userInputBalance = scan.nextDouble();
			System.out.println("Please enter starting balance for account: ");
			
			ps.setDouble(1, userInputBalance);
			
			
			accountsCreated = ps.executeUpdate();
			
		} catch (SQLException|IOException e) {
			//log.error(e.getMessage());
		} 
		return accountsCreated;
	}
	
	
	
	}


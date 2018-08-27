package com.revature.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.revature.dao.Account;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.Client;
import com.revature.dao.ClientDaoImpl;

public class TestMain {
	private static Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) throws IOException, SQLException {
		Connection con = DBConnectionUtil.getConnection();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM CLIENTS");
		
		ClientDaoImpl clientDao = new ClientDaoImpl();
		
		AccountDaoImpl accountDao = new AccountDaoImpl();
		System.out.println("Account: " + accountDao.getAccountById(1L, con));
		
		log.error("Flip");
		
//		for (Client c : clientDao.getClients()) {
//			System.out.println(c);
//		}
		
//		for (Account a : accountDao.getAccounts()) {
//			System.out.println(a);
//		}
		
//		while (rs.next()) {
//			System.out.println(rs.getString("username"));
//			
//		}
		con.close();
	}

}

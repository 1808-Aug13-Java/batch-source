package com.revature.main;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.CustomerDaoImpl;
import com.revature.model.Customers;
import com.revature.util.ConnectionUtil;

public class Driver {
	public static void main(String[] args) {
		
		Logger log = Logger.getRootLogger();
		Connection con;
	try {
		con = ConnectionUtil.getConnection();
		log.info(con.getMetaData().getDriverName());
	} catch (SQLException | IOException e) {
		log.error(e);
	}
	
		
	
	
		log.info("Please enter 1 to create new account or 2 to "
				+ "login to an existing account: ");
		Scanner menu = new Scanner(System.in);
		String my_choice = menu.nextLine();
		
		CustomerDaoImpl cdi;
        switch (my_choice) {

        case "1":
        	cdi = new CustomerDaoImpl();
    		int c = cdi.createCustomer();
    		
    		
        break;
        
        case "2":
        	Customers cust= new Customers();
    		while(cust.getUserName()==null) {
    			cdi = new CustomerDaoImpl();
    			cust = cdi.getCustomerByUserName();
    		}
    		
    		
    		}
    		  log.info("Please enter 1 to show balance, 2 to "
    	        		+ "deposit funds, 3 to withdraw funds, or 4 to exit: ");
    	        	Scanner new_menu = new Scanner(System.in);
    	        	String new_choice = new_menu.nextLine();
    	        	
    	        	switch(new_choice) {
    	        	case "1":
    	        		
    	        		Customers cust= new Customers();
    	        		while(cust.getUserName()==null) {
    	        			cdi = new CustomerDaoImpl();
    	        			cust = cdi.getCustomerByUserName();}
    	        		
    	        		break;
    	        		
    	        	case "2":
    	        		
    	        		log.info("Please enter your username");
    	        		Scanner scan1 = new Scanner(System.in);
        	        	String user = scan1.nextLine();
    	        		
    	        		log.info("Please enter amount to deposit");
    	        		Scanner scan2 = new Scanner(System.in);
        	        	float amount = scan2.nextFloat();
        	        	
    	        		cdi = new CustomerDaoImpl();
    	        		cdi.increaseBalance(user, amount);
    	        		
    	        		break;
    	        		
    	        	case "3":
    	        		log.info("Please enter your username");
    	        		Scanner scan3 = new Scanner(System.in);
        	        	 String user1 = scan3.nextLine();
    	        		
    	        		log.info("Please enter amount to withdraw");
        	        	float with_amt = scan3.nextFloat();
        	        	with_amt *= -1;
        	        	
    	        		cdi = new CustomerDaoImpl();
    	        		cdi.increaseBalance(user1,with_amt);
    	        		
    	        		break;
    	        		
    	        	case "4":
    	        		
    	        		log.info("Thank you, have a nice day");
    	        		System.exit(0);
    	        		
    	        	
    	        	
    	        	
        	 System.exit(0);
             break;}
        	
}}
        	
        	
	

	
	
      
        
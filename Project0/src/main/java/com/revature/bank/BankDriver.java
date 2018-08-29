/**
 * 
 */
package com.revature.bank;

import java.io.IOException;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.dao.*;
import com.revature.model.*;
import com.revature.util.ConnectionUtil;

import org.apache.log4j.Logger;

/**
 * @author nozuko
 * This is the central class that drives the code and calls all of our methods
 */
public class BankDriver {
	
	public static final String NOT_CUSTOMER = "You are not yet a customer with us.";
	public static final String NO_ACCOUNT = "Account not in our records.";
	
	private static Logger log = Logger.getRootLogger();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numUsers = 10;
		boolean loggedIn = false;
		boolean isCustomer = false;
		
		// where we will be testing our DAOs
		// make sure we are able to access our database
		try {
			Connection con = ConnectionUtil.getConnection();
			log.info(con.getMetaData().getDriverName());
		} catch (SQLException | IOException e) {
			log.error(e.getMessage());
		}
		//check getUser()
		UserDao udi = new UserDaoImpl();
		User client = new User();
		String usernameEntered = "";

			while(true) {
				log.info("Please select a number for one of the options:");
				log.info("1: Sign up (new users)");
				log.info("2: Deposit Money"); // requires account number for login
				log.info("3: Withdraw Money");
				log.info("4: Check my Balance");
				log.info("5: Exit");
				try {
					int choice = Integer.parseInt(sc.nextLine());

					switch(choice) {
					case 1:
						log.info("Thank you for choosing to bank with us.");
						log.info("\nPlease Enter a username: ");
						usernameEntered = sc.nextLine();
						
						if(udi.getUser().contains(udi.getUserById(usernameEntered))) {
							log.info("Unfortunately, this username is already taken. Try again.");
							break;
						}
						
						log.info("Please Enter a new password: ");
						String pw = sc.nextLine();
						
						log.info("Please Enter an initial amount for this account: ");
						float depAmt = Float.parseFloat(sc.nextLine());

						log.info("Thank you. Make sure to keep this password somewhere safe.");
						client = new User(usernameEntered, pw, depAmt);
						udi.createUser(client);
						numUsers++;
						loggedIn = true;
						isCustomer = true;
						break;
					case 2:
						if (!loggedIn) {
							log.info("\nPlease Enter your username: ");			
							usernameEntered = sc.nextLine();
						}
						
						log.info("Please Enter your password: ");
						pw = sc.nextLine();
						
						if(udi.getUser().contains(udi.getUserById(usernameEntered))&& udi.getUserById(usernameEntered).getPassword().equals(pw)) {
							isCustomer = true;
							log.info("Please Enter the amount you want to deposit: ");
							depAmt = Float.parseFloat(sc.nextLine());
							udi.getUserById(usernameEntered).deposit(udi.getUserById(usernameEntered),usernameEntered, depAmt);
						}
						if (numUsers == 0 || !udi.getUser().contains(udi.getUserById(usernameEntered)) || !udi.getUserById(usernameEntered).getPassword().equals(pw)) {
							log.info(NOT_CUSTOMER);
							isCustomer = false;
							loggedIn = false;
							break;
						}
						if(!isCustomer) log.info(NO_ACCOUNT);
					break;
					case 3:
						if (!loggedIn) {
							log.info("\nPlease Enter your username: ");			
							usernameEntered = sc.nextLine();
						}
						
						log.info("Please Enter your password: ");
						pw = sc.nextLine();
						
						if(udi.getUser().contains(udi.getUserById(usernameEntered))&& udi.getUserById(usernameEntered).getPassword().equals(pw)) {
							isCustomer = true;
							log.info("Please Enter the amount you want to withdraw: ");
							depAmt = Float.parseFloat(sc.nextLine());
							udi.getUserById(usernameEntered).withdraw(udi.getUserById(usernameEntered),usernameEntered, depAmt);	
						}
						if (numUsers == 0 || !udi.getUser().contains(udi.getUserById(usernameEntered)) || !udi.getUserById(usernameEntered).getPassword().equals(pw)) {
							log.info(NOT_CUSTOMER);
							isCustomer = false;
							loggedIn = false;
							break;
						}
						if(!isCustomer) log.info(NO_ACCOUNT);
					break;
					case 4:
						if (!loggedIn) {
							log.info("\nPlease Enter your username: ");			
							usernameEntered = sc.nextLine();
						}
						log.info("Please Enter your password: ");
						pw = sc.nextLine();
						
						if(udi.getUser().contains(udi.getUserById(usernameEntered))&& udi.getUserById(usernameEntered).getPassword().equals(pw)) {
							isCustomer = true;
							log.info("Your current balance is: $" + udi.getUserById(usernameEntered).getBalance());					
						}
						if (numUsers == 0 || !udi.getUser().contains(udi.getUserById(usernameEntered)) || !udi.getUserById(usernameEntered).getPassword().equals(pw)) {
							log.info(NOT_CUSTOMER);
							isCustomer = false;
							loggedIn = false;
							break;
						}
						if(!isCustomer) log.info(NO_ACCOUNT);
					break;
					case 5:
						log.info("You have chosen to exit. Thank you for banking with us. You are a valued customer. Goodbye :)");
						System.exit(0);
						break;
					default:
						break;
					} // end switch
				} catch (NumberFormatException e) {
					log.error(e.getMessage());
				} // end catch

			} // end while
	} // end main
} // end class

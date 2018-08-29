package com.revature.project0.drivers;

import org.apache.log4j.Logger;

import com.revature.project0.abstraction.CurrentUser;
import com.revature.project0.dao.UserDao;

public class LoginDriver {
	
	private LoginDriver() {}
	private static Logger log = Logger.getRootLogger();

	private static final String LOGIN_FAIL = 
			"Username invalid.\nTry again, or press [Enter] to return to login page.\n";
	private static final String LOGIN_PASS_FAIL = 
			"Password did not match.\nTry again, or press [Enter] to return to login page.\n";
	private static final String USERNAME_UNAVAILABLE = 
			"Username unavailable, try another username.\n";
	private static final String USERNAME_ERROR = 
			"Incorrect username length.";
	private static final String PASSWORD_ERROR = 
			"Incorrect password length";
	
	private static final String REGISTER_USER = 
			"Enter a unique username to represent you: \n(case insensitive, 8-20 characters)\n";
	private static final String REGISTER_PASSWORD = 
			"Enter a password between 8-20 characters";
	private static final String REGISTER_ADDRESS = 
			"Enter a street address\n";
	private static final String LOGIN_USER = 
			"Enter existing username:\n";
	private static final String LOGIN_PASS = 
			"Enter user's password:\n";
	
	private static final String USERNAME_AVAILABLE = 
			"Username Available";
	
	static void loginLoop() {
		boolean verified = false;
		String input;
		//CurrentUser c
		log.info(LOGIN_USER);
		input = MenuDriver.scan.nextLine().trim();
		UserDao ud = new UserDao();
		while(!verified) {
			if (input.length() < 1 || input.length() > 20 || ud.getCustomer(input.toUpperCase()) == null) {
				log.info(LOGIN_FAIL);
				input = MenuDriver.scan.nextLine().trim();
				if (input.equals(""))
					break;
			}
			else {
				MenuDriver.curr = ud.getCustomer(input);
				MenuDriver.curr.setUsername(input);
				log.info(LOGIN_PASS);
				input = MenuDriver.scan.nextLine().trim();
				while(!verified) {
					if (ud.checkPassword(MenuDriver.curr.getUsername(), input)) {
						verified = true;
					}
					else {
						log.info(LOGIN_PASS_FAIL);
						input = MenuDriver.scan.nextLine().trim();
						if (input.equals("")) {
							MenuDriver.curr = null;
							break;				
						}
					}
				}//End inner while
			}
		}//End outer while
		if (MenuDriver.curr != null)
			MenuDriver.mainLoop();
	}
	
	static void registerLoop() {
		boolean registered = false;
		String input;
		MenuDriver.curr = new CurrentUser();
		UserDao ud = new UserDao();
		while(!registered) {
			log.info(REGISTER_USER);
			input = MenuDriver.scan.nextLine().trim().toUpperCase();
			if (input.length() > 20 || input.length() < 8)
				log.info(USERNAME_ERROR);
			else if (ud.getCustomer(input) != null)
				log.info(USERNAME_UNAVAILABLE);
			else {
				log.info(USERNAME_AVAILABLE);
				MenuDriver.curr.setUsername(input);
				while(!registered) {
					log.info(REGISTER_PASSWORD);
					input = MenuDriver.scan.nextLine();
					if (input.length() > 20 || input.length() < 8)
						log.info(PASSWORD_ERROR);
					else {
						log.info(REGISTER_ADDRESS);
						MenuDriver.curr.setAddress(MenuDriver.scan.nextLine().trim());
						ud.addCustomer(MenuDriver.curr.getUsername(), input, MenuDriver.curr.getAddress());
						registered = true;
					}
				}//End inner while
			}
		}//End outer while
		MenuDriver.mainLoop();
	}
	
}

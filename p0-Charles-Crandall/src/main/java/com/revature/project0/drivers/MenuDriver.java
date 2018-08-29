package com.revature.project0.drivers;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.project0.abstraction.CurrentUser;
import com.revature.project0.dao.UserDao;
import com.revature.project0.main.MainDriver;

public class MenuDriver {

	private static final String GREETING = 
			"Welcome to the bank of [Revature.app.localname]!\n"
			+ "Type the number or name of the option you would like to perform, then press [Enter].\n";
	private static final String LOGIN_MENU = 
			"1. New User\n2. Returning user\n0. Exit";
	private static final String MAIN_MENU = 
			"1. Withdraw\n2. Deposit\n3. Internal Transfer\n4. History\n5. Edit Profile\n6. Logout\n";

	private static final String PURGE_EXECUTE =
			"It will be done, My Lord...";



	private static final String FAILURE = 
			"Incorrect value entered, please enter a number or word describing the desired action.\n";

	static Scanner scan;
	static CurrentUser curr;
	private static Logger log = Logger.getRootLogger();
	
	private MenuDriver() {}
	
	
	public static void initialize() {
		scan = new Scanner(System.in);
		String input = "";
		boolean stay = true;
		log.info(GREETING);

		while(stay) {
			log.info(LOGIN_MENU);
			
			input = scan.nextLine().trim();
			switch(input.trim().toLowerCase()) {
			case"exit": case"quit": case"0":
				stay = false;
				break;
			case"2": case"login":case"return":
				LoginDriver.loginLoop();
				break;
			case"1": case"register": case"new": case"new user":
				LoginDriver.registerLoop();
				break;
			
			default:
				log.info(FAILURE);
			}
		}
	}
	public static void order66() {
		String sql = "{call THE_PURGE}";
		try(Connection conn = ConnectionUtil.getConnection();
				CallableStatement cs = conn.prepareCall(sql)){
			cs.execute();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void withdrawLoop() {
		log.error("UNDER CONSTRUCTION");
	}
	
	private static void depositLoop() {
		boolean done = false;
		String input;
		while(!done) {
			log.error("UNDER CONSTRUCTION");
			done = true;
		}
	}
	
	private static void transferLoop() {
		log.error("UNDER CONSTRUCTION");
	}
	
	private static void userLoop() {//delete edit etc
		log.error("UNDER CONSTRUCTION");
	}
	

////Main menu structures	
	static void mainLoop() {
		log.info("Welcome, " + curr.getUsername() + "!\n");
		String input = "";
		boolean going = true;
		while (going) {
			log.info(MAIN_MENU);
			input = scan.nextLine();
			switch(input) {
			case"1": case"with": case"withdraw": case"withdrawl":
				withdrawLoop();
				break;
			case"2": case"dept": case"deposit":
				depositLoop();
				break;
			case"3": case"trans": case"transfer": case"internal":
				transferLoop();
				break;
			case"4": case"hist": case"history": 
				log.error("UNDER CONSTRUCTION");
				//TODO just call the transaction history
				break;
			case"5": case"user": case"edit":
				userLoop();
				break;
			case"6": case"logout": case"exit": case"quit":
				going = false;
				break;
			case"execute order 66":
				log.info(PURGE_EXECUTE);
				order66();
				break;
			default:
				log.info(FAILURE);
			}
		}//End while 
	}

}

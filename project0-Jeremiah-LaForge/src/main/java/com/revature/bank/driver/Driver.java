package com.revature.bank.driver;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bank.view.View;
import com.revature.model.Account;

public class Driver {
	private static Scanner console = new Scanner(System.in);
	private static Logger log = Logger.getRootLogger();

	/*
	 * input check spelling parsing issues Decimal issues implement view sort out
	 * why Ron can't log in how should database be connected? Persistence?
	 * 
	 */
	public static void main(String[] args) {

		String input = null;
		boolean validInput = false;
		boolean loggedIn = false;
		boolean exit = false;
		Account currUser = null;

		while (!exit) {
			log.info("To create a new account type 'create', to log in, type 'log in', and type 'exit' to log off.");
			input = console.nextLine();
			if (input.equalsIgnoreCase("create")) {
				while (!validInput) {
					validInput = View.createView();
				}
			} else if (input.equalsIgnoreCase("log in")) {
				while (!loggedIn) {
					currUser = View.logInView();
					if (currUser != null) {
						loggedIn = true;
					}
				}
				while (loggedIn) {
					loggedIn = View.loggedInMethod(currUser);
					if (!loggedIn) {
						exit = true;
					}
				}
			} else if (input.equalsIgnoreCase("exit")) {
				exit = true;
			} else {
				log.info("Invalid input. Please try again.");
			}
		}
		log.info("logged out successfully");

		console.close();
		
	}

}

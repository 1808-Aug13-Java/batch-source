package com.revature.bank.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bank.controller.Controller;
import com.revature.model.Account;

public class View {

	private static Scanner console = new Scanner(System.in);
	private static Logger log = Logger.getRootLogger();
	private static DecimalFormat df2 = new DecimalFormat(".##");

	private View() {
		super();
	}

	
	public static boolean createView() {
		ArrayList<String> accountCreate = new ArrayList<>();
		String input = null;

		log.info("Enter your prefered user name. Letters and numbers are valid,"
				+ " but special charaters and punctuation are not allowed."
				+ "Your user name will be used for log in.");
		input = console.nextLine();
		while (!input.matches("^[a-zA-Z0-9]*$")) {
			log.info("Invalid input. Please enter a valid user name");
			input = console.nextLine();
		}
		accountCreate.add(input);

		log.info("Enter your first name.");
		input = console.nextLine();
		while (!input.matches("^[a-zA-Z]*$")) {
			log.info("Invalid input. Please enter a valid user name");
			input = console.nextLine();
		}
		accountCreate.add(input);

		log.info("Enter your last name.");
		input = console.nextLine();
		while (!input.matches("^[a-zA-Z]*$")) {
			log.info("Invalid input. Please enter a valid user name");
			input = console.nextLine();
		}
		accountCreate.add(input);
		log.info(
				"Enter your password. Letters are case sensitive, and adding numbers is recomended. No special characters "
						+ "are allowed. Strong passwords have length greater than 14.");
		input = console.nextLine();
		while (!input.matches("^[a-zA-Z0-9]*$")) {
			log.info("Invalid input. Please enter a valid user name");
			input = console.nextLine();
		}
		accountCreate.add(input);

		log.info("What will be your initial deposit?"
				+ " Enter a numeric value greater than 0.01 in the format 0.00 to make deposit.");
		while (!input.matches("^\\d+\\.\\d{0,2}$") || (Double.parseDouble(input) <= 0.01)) {
			log.info("Invalid input. Please enter a valid number to deposit.");
			input = console.nextLine();
		}
		accountCreate.add(input);

		Account newAcc = Controller.createAccount(accountCreate.get(0), accountCreate.get(1), accountCreate.get(2),
				accountCreate.get(3), Double.parseDouble(accountCreate.get(4)));
		if (newAcc != null) {
			return true;
		}
		return false;
	}

	public static Account logInView() {

		Account acc;
		log.info("Enter your User name for log in.");
		String enteredU = console.nextLine();
		if (Controller.logIn(enteredU)) {
			acc = Controller.loadAccount(enteredU);
			return acc;
		}
		return null;

	}

	public static boolean loggedInMethod(Account acc) {
		String input = null;
		boolean withdrawal = false;

		log.info(
				"To log out type \"exit\". Type \"withdrawal\" to make a withdrawal, or \"deposit\" to make a deposit.");
		input = console.nextLine();
		if (input.equalsIgnoreCase("exit")) {
			return false;
		} else if (input.equalsIgnoreCase("withdrawal")) {
			log.info("Enter a numeric value greater than 0.01 in the format 0.00 to make withdrawl.");
			input = console.nextLine();
			while (!input.matches("^\\d+\\.\\d{0,2}$") || (Double.parseDouble(input) <= 0.01)) {
				log.info("Invalid input. Please enter a valid number to withdrawal.");
				input = console.nextLine();
			}
			withdrawal = Controller.withdrawal(Double.parseDouble(input), acc);
			if (withdrawal) {
				log.info("Withdrawal successful. New Ballance: " + df2.format(acc.getBallance()));
				return true;
			} else {
				log.info("Withdrawal failed. Current Ballance: " + df2.format(acc.getBallance()) + " amount requested: "
						+ input);
				return true;
			}
		} else if (input.equalsIgnoreCase("deposit")) {
			log.info("Enter a numeric value greater than 0.01 in the format 0.00 to make deposit.");
			input = console.nextLine();
			while (!input.matches("^\\d+\\.\\d{0,2}$") || (Double.parseDouble(input) <= 0.01)) {
				log.info("Invalid input. Please enter a valid number to deposit.");
				input = console.nextLine();
			}

			if (Controller.deposit(Double.parseDouble(input), acc)) {
				log.info("Deposit successful. New Ballance: " + df2.format(acc.getBallance()));
				return true;
			} else {
				log.info("Deposit failed. Current Ballance: " + df2.format(acc.getBallance()) + " amount requested: "
						+ input);
				return true;
			}
		} else {
			log.info("Invalid request. try again");
			return true;
		}
	}
}

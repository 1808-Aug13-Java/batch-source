package com.revature.banking;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class BankingDriver {

	public static ArrayList<User> users = new ArrayList<User>();
	public static Scanner sc = new Scanner(System.in);
	public static User activeUser = null;
	final static Logger logger = Logger.getLogger(BankingDriver.class);
	
	public static void main(String[] args) {
//		BasicConfigurator.configure();

		logger.info("Welcome to RevBanking");
		logger.info("");
		logger.info("Below is a list of commands that you can "
				+ "use inside the cmd prompt");
		logger.info("");
		
		users.add(new User("c@gmail.com", "crandon", "RevaturE1!"));
		
		String userInput = "";

		while(userInput != "exit") {
			listCommands();
			
			userInput = sc.nextLine().toLowerCase();
			// ensures a user can't
			// access class User's methods
			if(activeUser == null) {
				switch(userInput) {
					case "register":
						register();
						break;
					case "login":
						logIn();
						break;
					case "logout":
						logOut();
						break;
					case "exit":
						exitProgram();
					default:
						break;
				}
			} else {
				switch(userInput) {
					case "register":
						register();
						break;
					case "logout":
						logOut();
						break;
					case "deposit":
						// no authentication for depositing
						// free money i guess
						activeUser.getAccount().deposit();
						serializeUser();
						break;
					case "withdraw":
						
						if(activeUser.authenticated()) {
							activeUser.getAccount().withdraw();
							serializeUser();
						}
						
						break;
						
					case "view":
						logger.info("$" + 
							activeUser.getAccount().getBalance());
						logger.info("");
						break;
					case "exit":
						exitProgram();
						break;
					default: 
						break;
				}
			
			}
		
		
		}
		
		
		
		
		
	}
	
	
	public static void listCommands() {
		if(activeUser == null) {
			logger.info("register");
			logger.info("login");
			logger.info("exit");
		} else if (activeUser != null) {
			logger.info("logout");
			logger.info("deposit");
			logger.info("withdraw");
			logger.info("view");
		}
		
		logger.info("");
//		logger.info("If at any time you wish to view these again type help "
//				+ "or enter to exit");
		
		
		
	}
	
	
	
	public static void register() {
		// will add a unique user to the users list
		logger.info("");
		
		String email = getAnEmail();
		String username = getAUsername();
		
		boolean containsDuplicatedInfo = users
				.stream()
				.anyMatch((u) -> {
					return u.getEmail().equals(email) 
							|| u.getUsername().equals(username);
				});
		
		if(containsDuplicatedInfo) {
			logger.info("Sorry already a user with that email or username");
		}
		
		String password = containsDuplicatedInfo ? "" : getAPassword();
		
		User user = new User(email, username, password);
		
		if(!containsDuplicatedInfo) {
			users.add(user);
		} 
		
	}
	
	private static String getAnEmail() {
		
		logger.info("Enter an email:");
		String email = sc.nextLine();
		
		// get a valid email
		while(!validateEmail(email)) {
			logger.info("Enter a properly formatted email address");
			email = sc.nextLine();
		}
		
		return email;
	}
	
	private static boolean validateEmail(String email) {
		// regexp from OWASP regex wiki
		String emailValidationRegEx = "^[a-zA-Z0-9_+"
				+ "&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
		Pattern regExPattern = Pattern.compile(emailValidationRegEx);
		
		
		return regExPattern.matcher(email).matches();
	}
	
	
	// need to validate later, refer to OWASP
	private static String getAUsername() {
		logger.info("Enter a username: ");
		return sc.nextLine();
		
	}
	
	private static String getAPassword() {
		// regexp from OWASP regex wiki
		logger.info("Please enter a safe password:");
		String password = sc.nextLine();
		
		while(!validatePassword(password)) {
			logger.info("Enter a safer password:");
			password = sc.nextLine();
		}
		return password;
		
	}
	
	private static boolean validatePassword(String password) {
		String passwordValidationRegEx = "^(?=.*\\d)(?=.*[a-z])"
				+ "(?=.*[A-Z]).{4,20}$";
		
		Pattern regExPattern = Pattern.compile(passwordValidationRegEx);
		
		if (password == null) {
			return false;
		}
		
		return regExPattern.matcher(password).matches();
	}
	
	// logging in
	
	private static void logIn() {
		
		logger.info("");
		logger.info("Enter a username or email");
		String usernameOrEmail = sc.nextLine();
		User foundUser = null;
		
		for(User user: users) {
			
			if(usernameOrEmail.equals(user.getEmail())
					|| usernameOrEmail.equals(user.getUsername())) {
				
				foundUser = user;
				
			}
		}
		
		if(foundUser == null) {
			logger.info("Couldn't find a user w/ " + usernameOrEmail);
		} else {
			// if they authenticated themselves, login
			if(foundUser.authenticated()) {
				activeUser = foundUser;
			}
			
			// redisplay new messages
			logger.info("");
		}
	}
	
	private static void logOut() {
		activeUser = null;
	}
	
	private static void serializeUser() {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(activeUser.getUsername() + ".ser"))) {
			oos.writeObject(activeUser);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void exitProgram() {
		logger.info("Exiting...");
		activeUser = null;
		System.exit(0);
	}
	
	
	
}

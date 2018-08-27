package com.revature.validation;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class Validator {
	private static Scanner sc = new Scanner(System.in);
	private static Logger logger = Logger.getLogger(Validator.class);
	
//	ENSURE PROPER
//	EMAIL FORMAT
	
	public static String getAnEmail() {
		
		logger.info("Enter an email:");
		String email = sc.nextLine();
		
		// get a valid email
		while(!validEmail(email)) {
			logger.info("Enter a properly formatted email address");
			email = sc.nextLine();
		}
		
		return email;
	}

	public static boolean validEmail(String email) {
		// regexp from OWASP regex wiki
		String emailValidationRegEx = "^[a-zA-Z0-9_+"
				+ "&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
		Pattern regExPattern = Pattern.compile(emailValidationRegEx);
		
		
		return regExPattern.matcher(email).matches();
	}
	
	// VALIDATE USERNAME (PREVENT FROM SQL INJECTION)
	public static String getAUsername() {
		logger.info("Enter a username: ");
		String username = sc.nextLine();
		while(!validUsername(username)) {
			logger.info("Enter a proper username: ");
			username = sc.nextLine();
		}
		return username;
	}
	public static boolean validUsername(String username) {
		// regexp from OWASP regex wiki
		String emailValidationRegEx = "^[a-z0-9_-]{5,15}$";
		Pattern regExPattern = Pattern.compile(emailValidationRegEx);
	
		return regExPattern.matcher(username).matches();
	}
	
	// VALIDATE PASSWORD
	// 4-32 chars, upper/lower case letters, special chars, numbers 
	// and no consecutive letters
	public static String getAPassword() {
		// regexp from OWASP regex wiki
		logger.info("Please enter a safe password:");
		String password = sc.nextLine();
		
		while(!validPassword(password)) {
			logger.info("Enter a safer password:");
			password = sc.nextLine();
		}
		return password;
		
	}
	
	public static boolean validPassword(String password) {
		String passwordValidationRegEx = "^(?:(?=.*\\d)(?=.*[A-Z])"
				+ "(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])"
				+ "(?=.*[a-z])|(?=.*[^A-Za-z0-9])(?=.*[A-Z])"
				+ "(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))"
				+ "(?!.*(.)\\1{2,})[A-Za-z0-9!~<>,;:_=?*+#.\"&§%°()"
				+ "\\|\\[\\]\\-\\$\\^\\@\\/]{8,32}$";
		
		Pattern regExPattern = Pattern.compile(passwordValidationRegEx);
		
		if (password == null) {
			return false;
		}
		
		return regExPattern.matcher(password).matches();
	}
	
	public static String formatDecimals(float val) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(val);
	}

	public static boolean isCorrectScale(float amount) {
		Pattern p = Pattern.compile("^\\d+\\.\\d{0,2}$"); 
		System.out.println(String.valueOf(amount));
		Matcher matcher = p.matcher(String.valueOf(amount));  
		return matcher.matches();
	}
	

}

package com.revature.util;

import java.util.regex.Pattern;


public class Validator {
	
	private Validator() {
	
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
	
	public static boolean validUsername(String username) {
		// regexp from OWASP regex wiki
		String emailValidationRegEx = "^[a-z0-9_-]{5,15}$";
		Pattern regExPattern = Pattern.compile(emailValidationRegEx);
	
		return regExPattern.matcher(username).matches();
	}
	
	// VALIDATE PASSWORD
	// 4-32 chars, upper/lower case letters, special chars, numbers 
	// and no consecutive letters	
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

}

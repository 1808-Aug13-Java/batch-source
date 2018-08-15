package com.revature.exceptions;

// checked exception
public class incorrectCharacterInNameException extends Exception {
	private static final long serialVersionUID = 2L;

	public incorrectCharacterInNameException(String message) {
		super(message);
	}
}

package com.revature.exceptions;

// checked exception
public class IncorrectCharacterInNameException extends Exception {
	private static final long serialVersionUID = 2L;

	public IncorrectCharacterInNameException(String message) {
		super(message);
	}
}

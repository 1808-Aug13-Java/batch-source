package com.revature.exceptions;

/*
 * This exception class directly extends exception and therefore is checked exception; caught at compile time
 */
public class ImpossibleColorException extends Exception {
	
	public ImpossibleColorException() {
		super();
	}
	
	public ImpossibleColorException(String message) {
		super(message);
	}

}

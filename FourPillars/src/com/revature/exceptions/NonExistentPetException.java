package com.revature.exceptions;

public class NonExistentPetException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	// this is an unchecked exception
	// displays a message (defined elsewhere) when x conditions are met
	public NonExistentPetException(String message) {
		super(message);
	}
	
	public NonExistentPetException() {
		super();
	}
}

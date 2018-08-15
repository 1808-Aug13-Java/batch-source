package com.revature.exceptions;

public class NoVocalizationException extends Exception {
	
	private static final long serialVersionUID = 1L;

	// this is a checked exception
	// displays a message (defined elsewhere) when x conditions are met
	public NoVocalizationException(String message) {
		super(message);
	}
	
	public NoVocalizationException() {
		super();
	}
}

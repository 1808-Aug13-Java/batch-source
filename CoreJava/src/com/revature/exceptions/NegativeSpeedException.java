package com.revature.exceptions;

public class NegativeSpeedException extends Exception {
	
	// any exception that extends Exception is checked; any exception that extends RuntimeException is unchecked
	public NegativeSpeedException() {
		super();
	}
	
	public NegativeSpeedException(String message) {
		super(message);
	}
	
	// if we extend RuntimeException, we do not have to handle the exception
	// in the same way we do with a checked exception
	
}

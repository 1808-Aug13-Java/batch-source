package com.revature.exceptions;

// this is a checked exception. For no good reason, only objects of the Lion class can throw it.
public class NonbinaryGenderException extends Exception {

	private static final long serialVersionUID = 1L; // this constant belongs 
	// to the NonbinaryGenderException class and was only added to remove an error message

	public NonbinaryGenderException() {
		super();
	}
	
	public NonbinaryGenderException(String message) {
		super(message);
	}
 }

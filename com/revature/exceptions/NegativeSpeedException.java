package com.revature.exceptions;

// NegativeSpeedException extends from parent class Exception
// This is a checked Exception
public class NegativeSpeedException extends Exception{

	private static final long serialVersionUID = 1L;
	
	// default exception constructor
	public NegativeSpeedException() {
		super();
	}
	
	// exception constructor that calls parent constructor w/ String param
	public NegativeSpeedException(String message) {
		super(message);
		
	}
}

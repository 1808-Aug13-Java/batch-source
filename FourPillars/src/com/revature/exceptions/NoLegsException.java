package com.revature.exceptions;

public class NoLegsException extends Exception {
	
	/** A generated serial ID required by extending the Exception class. */
	private static final long serialVersionUID = -8113670387114041690L;
	
	/** A default message for this exception. */
	private static final String DEFAULT_MESSAGE = 
			"This has no legs. ";
	
	/** Constructs a new NoLegsException with a default message. */
	public NoLegsException() {
		super(DEFAULT_MESSAGE);
	} // end of constructor NoLegsException
	
	
	/** Constructs a new NoLegsException with the specified message. 
	 * @param message The message of the exception */
	public NoLegsException(String message) {
		super(message);
	} // end of constructor NoLegsException
	
	
} // end of class NoLegsException



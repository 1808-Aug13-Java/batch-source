package com.revature.exceptions;


/** This is a runtime exception that is thrown when a negative number is 
 * provided as a parameter when one should not have. */
public class NegativeNumberException extends IllegalArgumentException {
	
	
	/** A generated serial ID required by extending the IllegalArgumentException
	 * class. */
	private static final long serialVersionUID = -4598696230690765490L;
	
	/** A default message for this exception. */
	private static final String DEFAULT_MESSAGE = 
			"Negative Numbers are not allowed. ";

	/** Constructs a new NegativeNumberException with a default message. */
	public NegativeNumberException() {
		super(DEFAULT_MESSAGE);
	} // end of constructor NegativeNumberException
	
	
	/** Constructs a new NegativeNumberException with the specified message. 
	 * @param message The message of the exception */
	public NegativeNumberException(String message) {
		super(message);
	} // end of constructor NegativeNumberException
	
	
	/** Constructs a new NegativeNumberException that includes the specified 
	 * value along with the default message. 
	 * @param value - The value that caused the NegativeNumberException */
	public NegativeNumberException(double value) {
		super(DEFAULT_MESSAGE + " Provided Value: " + value);
	} // end of constructor NegativeNumberException
	
	
} // end of class NegativeNumberException

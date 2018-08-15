package com.revature.animals;
/*
 * If a client can reasonably be expected to recover from an exception,
 *  make it a checked exception. If a client cannot do anything to 
 *  recover from the exception, make it an unchecked exception.
 */
public class NegativeValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NegativeValueException(String message)
	{
		super(message);
	}
}

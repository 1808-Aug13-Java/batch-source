package com.revature.exceptions;

public class NegativeLegsException extends Exception{

	private static final long serialVersionUID = 1L;
	//Here I have a checked exception that throws whenever 
	//the value of numberLegs tries to be set to below 0.
	public NegativeLegsException(String message) {
		super(message);
	}
}

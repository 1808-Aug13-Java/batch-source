package com.revature.exceptions;

public class AccelerationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	//here I have an unchecked exception that throws whenever an 
	//Animal is being set to accelerate by a factor of 0.
	public AccelerationException(String message) {
		super(message);
	}
}

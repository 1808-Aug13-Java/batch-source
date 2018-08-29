package com.revature.exceptions;

public class NegativeNumberException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegativeNumberException() {
		super();
	}
	
	public NegativeNumberException(String message) {

		super(message);

	}

}

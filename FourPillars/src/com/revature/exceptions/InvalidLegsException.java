package com.revature.exceptions;

public class InvalidLegsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidLegsException() {
		super();
	}
	
	public InvalidLegsException(String message) {
		super(message);
	}

}

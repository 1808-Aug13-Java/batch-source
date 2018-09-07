package com.revature.exceptions;

public class AnimalException extends Exception{

	/**
	 * default serial version
	 */
	private static final long serialVersionUID = 1L;

	public AnimalException() {
		super();
	}
	
	public AnimalException(String message) {
		 super(message);
	}
}

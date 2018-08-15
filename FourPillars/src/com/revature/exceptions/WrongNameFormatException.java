package com.revature.exceptions;

public class WrongNameFormatException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongNameFormatException(){
		super();
		
	}
	
	public WrongNameFormatException(String message) {
		super(message);
	}

}

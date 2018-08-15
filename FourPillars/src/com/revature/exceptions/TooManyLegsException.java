package com.revature.exceptions;

public class TooManyLegsException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TooManyLegsException(){
		super();
		
	}
	
	public TooManyLegsException(String message) {
		super(message);
	}
}

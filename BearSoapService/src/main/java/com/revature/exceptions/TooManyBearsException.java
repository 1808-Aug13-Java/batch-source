package com.revature.exceptions;

public class TooManyBearsException extends Exception{

	private String message = "too many books";
	
	public TooManyBearsException() {
		super();
	}
	
	public TooManyBearsException(String message) {
		super(message);
	}
	
	
	
}

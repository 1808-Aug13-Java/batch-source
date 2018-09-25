package com.revature.exceptions;

public class StoreFullException extends Exception {
	
	private String message = "too many toys";
	
	public StoreFullException() {
		super();
	}
	
	public StoreFullException(String message) {
		super(message);
	}


}
package com.revature.exceptions;

public class GarageFullException extends Exception {
	
	private String message = "too many cars";
	
	public GarageFullException() {
		super();
	}
	
	public GarageFullException(String message) {
		super(message);
}
}

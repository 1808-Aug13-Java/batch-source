package com.revature.exception;

public class AddressAlreadyExistsException extends Exception{
	String message= "Already there";
	
	public AddressAlreadyExistsException() {
		super();
	}
	
	public AddressAlreadyExistsException(String message) {
		super(message);
	}
	

}

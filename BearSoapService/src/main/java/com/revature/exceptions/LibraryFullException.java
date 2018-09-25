package com.revature.exceptions;

public class LibraryFullException extends Exception{

	private String message = "too many books";
	
	public LibraryFullException() {
		super();
	}
	
	public LibraryFullException(String message) {
		super(message);
	}
	
	
	
}

package com.revature.exceptions;

public class LibraryFullException extends Exception{

	private String message="Too many books";
	
	public LibraryFullException() {
		super();
	}
	
	public LibraryFullException(String message) {
		super(message);
	}

}

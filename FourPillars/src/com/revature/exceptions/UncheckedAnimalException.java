package com.revature.exceptions;

// this is an unchecked exemption. It does not need to be handled.
// as of now, only objects of the Snake class can throw it.
public class UncheckedAnimalException extends RuntimeException {
	
	private static final long serialVersionUID = 1L; // this constant was only added 
	// to avoid an error message

	public UncheckedAnimalException() {
		super();
	}
}

package com.revature.exceptions;

// if we extend RuntimeException, we do not have to handle the exception 
// in the same way we do with an checked exception
public class NegativeSpeedException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NegativeSpeedException() {
		super();
	}
	
	public NegativeSpeedException(String message) {
		super(message);
	}

}

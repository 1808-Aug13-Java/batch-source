package com.revature.exceptions;

/*
 * This exception class directly extends RuntimeException and therefore is unchecked exception; caught at runtime
 */
public class ImpossibleSpeedException extends RuntimeException {

	public ImpossibleSpeedException() {
		super();
	}
	
	public ImpossibleSpeedException(String message) {
		super(message);
	}

}

package com.revature.customexceptions;

public class CustomUncheckedException extends RuntimeException {
	public CustomUncheckedException() {
		super();
	}
	
	public CustomUncheckedException(String message) {
		super(message);
	}

}

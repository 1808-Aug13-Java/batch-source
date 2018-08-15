package com.revature.fourpillars;

public class NotSleepyRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4831103620720126416L;

	public NotSleepyRuntimeException() {
		super();
	}
	
	public NotSleepyRuntimeException(String message) {
		super(message);
	}
}
//this is a custom unchecked, Runtime exception

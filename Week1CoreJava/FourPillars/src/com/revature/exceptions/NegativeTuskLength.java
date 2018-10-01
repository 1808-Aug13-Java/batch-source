package com.revature.exceptions;

public class NegativeTuskLength extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NegativeTuskLength() {
		super();
		System.out.println("A negative tusk length was given");
	}

	public NegativeTuskLength(String message) {
		super(message);
	}
	
}

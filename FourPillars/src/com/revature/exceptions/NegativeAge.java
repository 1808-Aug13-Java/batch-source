package com.revature.exceptions;

public class NegativeAge extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NegativeAge() {
		super();
		System.out.println("Negative age");
	}
	
	public NegativeAge(String message) {
		super(message);
	}

}

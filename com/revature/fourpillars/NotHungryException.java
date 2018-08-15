package com.revature.fourpillars;

public class NotHungryException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1821162848423149759L;
	public NotHungryException() {
		super();
	}
	public NotHungryException(String message) {
		super(message);
	}
//this is a type of custom checked exception
}

package com.revature.exceptions;

public class WrongTerrainException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongTerrainException() {
		super();
	}
	
	public WrongTerrainException(String message) {
		super(message);
	}
}

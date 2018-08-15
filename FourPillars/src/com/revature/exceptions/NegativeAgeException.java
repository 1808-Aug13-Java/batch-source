package com.revature.exceptions;

// unchecked exception
public class NegativeAgeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NegativeAgeException(String message) {
		super(message);
	}
}

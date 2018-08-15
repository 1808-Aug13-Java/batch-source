package com.revature.exceptions;

// Runtime Exceptions are unchecked exceptions
public class NegativeNumApendagesException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NegativeNumApendagesException() {
		super();
	}
	
	public NegativeNumApendagesException(String m) {
		super(m);
	}
}

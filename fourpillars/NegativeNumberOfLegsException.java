package com.revature.fourpillars;

//unchecked exception class
public class NegativeNumberOfLegsException extends RuntimeException{
	public NegativeNumberOfLegsException(String message) {
		super(message);
	}
}
package com.revature.exception;

public class AddressNotPresentException extends Exception{

	String message= "This address is not present it the direcory";
	
	public AddressNotPresentException() {
		super();
	}

	public AddressNotPresentException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public AddressNotPresentException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AddressNotPresentException(String arg0) {
		super(arg0);
	}

	public AddressNotPresentException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	

}

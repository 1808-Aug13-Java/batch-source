package com.revature.henry;

public class MassTooLargeException extends RuntimeException{
	
	    public MassTooLargeException(String message) {
	        super("Mass cannot be that large");
	    }

	}
package com.revature.animals;

//This is a marked exception
public class NegativeHungerException extends Exception {

	public NegativeHungerException()
	{
		super();
	}
	
	public NegativeHungerException(String message)
	{
		super(message);
	}
}

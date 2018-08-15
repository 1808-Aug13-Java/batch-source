package com.revature.animals;

//This is an unmarked exception
public class TooMuchFoodException extends RuntimeException {

	public TooMuchFoodException()
	{
		super();
	}
	
	public TooMuchFoodException(String message)
	{
		super(message);
	}
}

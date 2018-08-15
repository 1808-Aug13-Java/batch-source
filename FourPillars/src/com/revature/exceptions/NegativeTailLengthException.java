package com.revature.exceptions;



public class NegativeTailLengthException extends Exception //Exception for checked exception
{
	private static final long serialVersionUID = 1L;
	
	//constructor
	public NegativeTailLengthException(String message)
	{
		super(message); 		//Super is a function that lets you use the super class' constructor.
								//Super takes in the same arguments as ones that go in superclass' constructor
								//and in this case Exception is the super class and it needs a
		//String message in its constructor
	}
}

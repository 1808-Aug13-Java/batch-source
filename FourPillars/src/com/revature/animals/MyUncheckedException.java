package com.revature.animals;
/*
 * Runtime exceptions represent problems that are the result of a 
 * programming problem, and ass such, the API client code cannot
 * reasonably be expected to recover from them or to handle them in any
 * way. Such problems include arithmetic exceptions, such as dividing 
 * by zero; pointer exceptions, such as trying to access an object 
 * through a null reference; and indexing exceptions, such as 
 * attempting to access an array element through an index that is too
 *  large or too small.
 */

public class MyUncheckedException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*this exception corrects a unhandeled exception because it is a hardcoded error
	 * where the user is not expected to correct, so we throw the exception and
	 * then give the valid default values without user input
	 */
	public MyUncheckedException(String message,Animals it, String s, int speed)
	{
		super(message);
		it.setSpeed(speed);
		it.setType(s);
		
	}

}

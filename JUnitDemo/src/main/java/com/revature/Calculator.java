package com.revature;

public class Calculator {

	/*
	 * we're going to make an add method
	 * accepts a string and returns int
	 * 
	 * we want this method to take 0, 1, or 2 numbers delimited by a comma
	 * 
	 * providing 0 params an empty string will return 0
	 * providing 1 number will return that number
	 * providing 2 numbers will return their sum
	 * providing an invald input will return -1
	 * 
	 * 
	 */
	
	public static int add(String input) throws RuntimeException {
		int sum = 0;
		if(input.equals("")) {
			return 0;
		}
		
		String[] numbers = input.split("[\\n,]+");
		
		for(String number : numbers) {
			
			
			try {
				
				int parsedNumber = Integer.parseInt(number);
				
				if(parsedNumber > 1000) {
					continue;
				}
				
				if(parsedNumber < 0) {
					throw new RuntimeException("No negative numbers");
				}
				
				sum += parsedNumber;
				
			} catch (NumberFormatException e){
				sum = -1;
				return sum;
			}
			
		}
		
		
		return sum;
	}
	
	
  
	
}

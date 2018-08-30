package com.revature;

public class Calculator {
	
	/*
	 * we're going to make an add method which accepts a string and 
	 * returns an int
	 * 
	 * we want this method to take 0, 1, or 2 numbers delimited by a comma
	 * 
	 * providing 0 number (an empty string) will return 0
	 * providing 1 number will return that number
	 * providing 2 numbers will return their sum
	 * providing an invalid input will return -1
	 * 
	 */
	
	public static int add(String input) {
		int sum = 0;
		if(input.equals("")) {
			return sum;
		}
		
		String[] numbersArray = input.split(",");
		
		if(numbersArray.length == 1) {
			try {
				sum = Integer.parseInt(numbersArray[0]);
			} catch (NumberFormatException e) {
				sum = -1;
			}
		}
		
		if(numbersArray.length == 2) {
			try {
				sum = Integer.parseInt(numbersArray[0])+Integer.parseInt(numbersArray[1]);
			} catch (NumberFormatException e) {
				sum = -1;
			}
		}
		
		return sum;
	}

}

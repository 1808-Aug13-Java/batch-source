package com.revature;

public class Calculator {
	
/*
 * 
 * we're going to make an add method which accepts a string and returns an int
 * 
 * we want this method to take 0, 1, or 2 numbers delimited by a comma
 * 
 * providing 0 numbers (an empty string) will return 0
 * providing 1 number will return that number
 * providing 2 numbers will return their sum
 * providing an invalid input will return -1
 * 
 * - Two numbers, newline delimited, returns the sum
- Three numbers, delimited either way, returns the sum
- Negative numbers throw an exception
- Numbers greater than 1000 are ignored
- A single char delimiter can be defined on the first line (e.g. //# for a ‘#’ as the delimiter)
- A multi char delimiter can be defined on the first line (e.g. //[###] for ‘###’ as the delimiter
 * 
 * 
 */
	
	public static int add(String input) {
		int sum = 0;
		if(input.equals("")) {
		return sum;
		} 
		
		String numbersArray1[] = input.split(",");
		String numbersArray2[] = input.split("\n");
		
		if(numbersArray1.length == 1) {
			try {
			sum = Integer.parseInt(numbersArray1[0]);
		} catch (NumberFormatException e) {
			sum = -1;
		}
		if(numbersArray1.length == 2) {
			try {
				sum = Integer.parseInt(numbersArray1[0]) + Integer.parseInt(numbersArray1[1]);
			} catch (NumberFormatException e) {
				sum = -1;
			}
		}

		if(numbersArray2.length == 1) {
			try {
			sum = Integer.parseInt(numbersArray2[0]);
			} catch (NumberFormatException e) {
			sum = -1;
			}
		if(numbersArray2.length == 2) {
			try {
				sum = Integer.parseInt(numbersArray2[0]) + Integer.parseInt(numbersArray2[1]);
			} catch (NumberFormatException e) {
				sum = -1;
			}
		}
		}
		}

		return sum;
	}
	
	
}

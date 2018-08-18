package com.revature;

public class Calculator {
	// add method should accept a string and return an int
	// method should take 0, 1, or 2 methods, delimited by a comma
	// providing 0 parameters (an empty string) will return 0
	// providing 1 number will return that number
	// providing 2 numbers will return their sum
	// providing an invalid input will return -1
	
	public static int add(String input) {
		int sum = 0;
		if (input.equals("")) {
			return sum;
		}
		
		String[] numbersArray = input.split("[,\n]+");
		
		// attempt at Negative Number test
//		for (String s : numbersArray) {
//			if (Integer.parseInt(s) < 0) {
//				throw new ArithmeticException();
//			}
//		}
		
		for (int i = 0; i < numbersArray.length; i++) {
			if (Integer.parseInt(numbersArray[i]) > 1000) {
				
			}
		}
		
		if (numbersArray.length == 1) {
			try {
				sum = Integer.parseInt(numbersArray[0]);
			} catch (NumberFormatException e) {
				sum = -1;
			}
		}
		
		if (numbersArray.length == 2) {
			try {
				sum = Integer.parseInt(numbersArray[0]) + Integer.parseInt(numbersArray[1]);
			} catch (NumberFormatException e) {
				sum = -1;
			}
		}
		
		if (numbersArray.length == 3) {
			try {
				sum = Integer.parseInt(numbersArray[0]) + Integer.parseInt(numbersArray[1]) + Integer.parseInt(numbersArray[2]);
				System.out.println(Integer.parseInt(numbersArray[0]));
				System.out.println(Integer.parseInt(numbersArray[1]));
				System.out.println(Integer.parseInt(numbersArray[2]));

			} catch (NumberFormatException e) {
				sum = -1;
				System.out.println(sum);
			}
		}
		
		
		return sum;
	}
}

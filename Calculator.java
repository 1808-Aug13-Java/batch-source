package com.revature;

public class Calculator {

	/*
	 * we're going to make an add method which accepts a string and returns an int
	 * 
	 * we want this method to take 0, 1, or 2 numbers delimited by a comma
	 * 
	 * providing 0 number (an empty string) will return 0
	 * providing 1 number will return that number
	 * providing 2 numbers will return their sum
	 * providing an invalid input will return -1
	 */

	public static int add(String input) {
		int sum = 0;

		// check if input is empty
		if (input.equals("")) {
			return 0;
		}

		String[] numbersArray = input.split("[\\W]++");

		for (String i : numbersArray) {
			try {
				if (Integer.parseInt(i) <= 1000) { // only add the character that is not > 1000
					sum += Integer.parseInt(i);
				}
			} catch (NumberFormatException e) {
				sum = -1;
			}
		}

		//for some reason, when this method gets added, it makes other test cases fail. Will continue next week...
		
//		int count = 0;
//		while (count < numbersArray.length) {
//			if (Integer.parseInt(numbersArray[count]) < 0) {
//				throw new RuntimeException();
//			}
//			count++;
//		}
		
		return sum;
	}

}

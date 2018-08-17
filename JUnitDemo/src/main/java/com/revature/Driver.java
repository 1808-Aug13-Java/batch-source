package com.revature;

public class Driver {
	
	public static void main(String[] args) {
		
		
		//Just testing to see if my mistake was made in the method
		String input = "7,2";
		String numbersArray[] = input.split(",");
		
		int sum = Integer.parseInt(numbersArray[0]) + Integer.parseInt(numbersArray[1]);
		System.out.println(Integer.parseInt(numbersArray[0]));
		System.out.println(sum);
	}

}

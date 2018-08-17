package com.revature;

public class Numbers {
	
	
	public static int returnTwoNumber(String input) {
		input = "r\n5";
		String[] numbersArray = input.split("\n");
		int num1 =0;
		int num2 =0;
			if(numbersArray.length==2) {
				try {
					num1 = Integer.parseInt(numbersArray[0]);
					num2 = Integer.parseInt(numbersArray[1]);
				}catch(NumberFormatException e) {
				
			};
		
		}
			return num1+num2; 
	}
}

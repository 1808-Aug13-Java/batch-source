package com.revature;

public class Calculator {

	public static int add(String input) {
		int sum = 0;
		String[] strArray;
		if (input.isEmpty())
			return sum;
		try {
			if(Character.isDigit(input.charAt(0)) || input.charAt(0) == '-') {
				//do basic stuff [,\n]
				strArray = input.split("[,\n]\\s*");
			}
			else {
				//first line delimited
				int newline = input.indexOf("\n");
				String delim = input.substring(0,newline);
				strArray = input.split(delim);
			}
			
			for (String word: strArray) {
				String str = word.trim();
		System.out.println("String:****************** " + word);
				int tmpInt;
				if (str.matches("-?\\d+")) { 
					tmpInt = Integer.parseInt(str);
		System.out.println(str + " to try: "  + tmpInt);
				}		
				else
					continue;
				if (tmpInt < 0)
					throw new NumberFormatException("Does not accept Negative Numbers");
				if (tmpInt > 1000)
					continue;
				System.out.println("Test after 1000");
				
				sum += tmpInt;
			}
		} catch(Exception e) {
			
		}
		return sum;
	}
}

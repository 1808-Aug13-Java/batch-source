package com.revature.types;

public class AutoBoxing {
	public static void main(String[] args) {
//		 boxing
		int num1 = 5;
		Integer num2 = new Integer(num1);
		
		
		// Unboxing
		Integer num3 = new Integer(3);
		int num4 = num3.intValue();
		
		// autoboxing
		int num5 = 12;
		Integer num6 = num5;
		
		// auto unboxing
		Integer num7 = new Integer(10);
		int num8 = num7;
	}
	
}
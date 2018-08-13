package com.revature.operators;

public class OperatorDriver {
	public static void main(String[] args) {
		
		int a =10;
		
		System.out.println("a++:" + a++ + " and the value of a is now: " + a);
		System.out.println("++a:" + ++a + " and the value of a is now: " + a);
		System.out.println("a--:" + a-- + " and the value of a is now: " + a);
		System.out.println("--a:" + --a + " and the value of a is now: " + a);

		int x = 6;
		int y = 7;
		int z = 8;
		
		String s1 = x>y ? "Hello" : "World";
		System.out.println(s1);
	}
}

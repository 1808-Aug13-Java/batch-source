package com.revature.operators;

public class OperatorDriver 
{
	public static void main(String[] args)
	{
		int a = 10;
		System.out.println("a++: "  + a++ + " and the value of a is now: " + a);
		System.out.println("++a: "  + ++a + " and the value of a is now: " + a);
		
		System.out.println("a--: "  + a-- + " and the value of a is now: " + a);
		System.out.println("--a: "  + --a + " and the value of a is now: " + a);
		
		int x = 6, y = 7, z = 8;
		String s1 = x > y ? "Hello" : "World";
		System.out.println(s1);
		
		String s2 = (x<y) && (y>z) ? "Yellow" : "Green";
		System.out.println(s2);
		
		String s3 = (x*2 > z) || (y<10) ? "Cat" : "Dog";
		System.out.println(s3);
	}
}

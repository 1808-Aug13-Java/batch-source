package com.revature.types;

public class DefaultDriver {

	static boolean bool;
	static byte by;
	static char ch;
	static double d;
	static String str;
	static boolean[] arr = new boolean[4];
	
	public static void main(String[] args) {
		
		System.out.println("Bool: " + bool);
		System.out.println("By: " + by);
		System.out.println("Ch: " + ch);
		System.out.println("Double: " + d);
		System.out.println("Str: " + str);
//		System.out.println("Arr: " + arr);
		System.out.println(arr[0]);
		
	}

}

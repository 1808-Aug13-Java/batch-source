package com.revature.collections;

import java.util.ArrayList;

public class Generics {
	public static void main(String[] args) {
		
		// we use generics as a placeholder to parameterize type
		ArrayList arrList = new ArrayList();
		
		
		// arrList.add("test");Integer i = (Integer) arrList.get(0);
		
		// System.out.println(arrList);
		
		// generics allow for type safety
		
		ArrayList<String> strArrList = new ArrayList<String>();
		
		strArrList.add("test");
		// Integer i = (Integer) strArrList.get(0); //compile time safety
		
		Integer[] iArr = {1,2,3,4};
//		Not type independent
//		printMe(iArr);
		
		
		printMe(iArr);
	}
	
//	public static void printMe(Integer[] arr) {
//		for(Integer i : arr) {
//			System.out.println(i);
//		}
//	}
	// generics parameterize types in our method declarations
	public static <T> void printMe(T[] t) {
		for(T i : t) {
			System.out.println(i);
		}
	}
}

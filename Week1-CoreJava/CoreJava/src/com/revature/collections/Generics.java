package com.revature.collections;

import java.util.ArrayList;

public class Generics {

	public static void main(String[] args) {
		
		//we use generics as placeholder to parameterize type
		ArrayList arrList = new ArrayList();
		arrList.add("test");
//		Integer i = (Integer) arrList.get(0);
		
		//using generics we can assure type safety at compile time
		ArrayList<String> strArrList = new ArrayList<String>();
		strArrList.add("test");
		//Integer i = (Integer) strArrList.get(0);
		
		Integer[] iArr = {1,2,3,4};
		printMe(iArr);
		
		Character[] cArr = {'h','e','l','l','o'};
		printMe(cArr);
		

	}
	
//	public static void printMe(Integer[] arr) {
//		for(Integer i: arr) {
//			System.out.println(i);
//		}
//	}
//	
//	public static void printMe(Character[] arr) {
//		for(Character i: arr) {
//			System.out.println(i);
//		}
//	}

	//use generics to parameterize types in our method declarations
	public static <U> void printMe(U[] arr) {
		for(U i: arr) {
			System.out.println(i);
		}
	}
	
}

package com.revature.collections;

import java.util.*;

public class Generics {

	public static void main(String[] arg) {
		
		//we use generics as a place holder to parameterize
//		ArrayList arrList = new ArrayList();
		
//		arrList.add("test");
//		Integer i = (Integer) arrList.get(0);
		
		//using generics we can insure type safety at compile time
		ArrayList<String> arrList2 = new ArrayList<String>();
		//Integer r = (Integer) arrList2.get(0); this won't compile
		
		Integer[] iArr = {1,2,3,4,5};
		Character[] cArr = {'a','b','c','d'};
		
		printMe(iArr);
		printMe(cArr);
		
	}
	
	public static <T> void printMe(T[] arr) {
		 for(T i: arr) {
			 System.out.print(" " +i + " ");
		 }
		 System.out.println("");
	}
	
}

package com.revature.strings;

public class StringDriver {
	public static void main(String[] args) {
		
		String string1 = "I'm a string!";
		String string2 = "I'm a string!";
		
		System.out.println("Objects are equal: " + string1.equals(string2));
		System.out.println("Objects are ==: " + (string1 == string2));
		
		//modifying the string values will reference a new object in the pool
		String alsoString1 = string1;
		
		string1 = string1 + "!";
		System.out.println("string1: " + string1);
		System.out.println("alsoString1: " + alsoString1);
		
		System.out.println("string1 hash: " + string1.hashCode());
		System.out.println("alsoString1 hash: " + alsoString1.hashCode());
		System.out.println("string1 equals alsoString1?: " + string1.equals(alsoString1));
		System.out.println("string1 == alsoString1?: " + string1 == alsoString1);
		System.out.println("////////////////////////////////////////////////");
		
		//mutable sequences of characters
		StringBuilder strBuilder1 = new StringBuilder("I'm a stringbuilder object");
		StringBuilder strBuilder2 = new StringBuilder("I'm a stringbuilder object");
		
		System.out.println(strBuilder1.hashCode());
		System.out.println(strBuilder2.hashCode());
		System.out.println(strBuilder1.equals(strBuilder2));
		System.out.println(strBuilder1.toString().equals(strBuilder2.toString()));
		System.out.println("");
		
		/*
		 * if we were to convert the stringbuilders to strings they would again 
		 * be pointing to the same place in the string pool
		 */
		
		System.out.println("strbuilder1" + strBuilder1.toString().hashCode());
		System.out.println("strbuilder2" + strBuilder2.toString().hashCode());
		System.out.println( );
		
	}

}

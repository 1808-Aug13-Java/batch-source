package com.revature.strings;

public class StringDriver {

	public static void main(String[] args) {
		
		String string1 = "I'm a string!";
		String string2 = "I'm a string!";
		
		// strings have the same value as well as the same reference
		System.out.println("Objects are equal: " + string1.equals(string2));
		System.out.println("Objects are ==: " + (string1==string2));	
		System.out.println("String1 hashcode: "+ string1.hashCode());
		System.out.println("String2 hashcode: " + string2.hashCode());
		
		// modify the strings value - this will reference a new object in the string pool
		String alsoString1 = string1;
		string1 = string1 + "!";
		System.out.println(string1);
		System.out.println(alsoString1);
		// this is reflected with a new hashcode
		System.out.println(string1.hashCode());
		System.out.println(alsoString1.hashCode());
		// also by comparing it to a variable referencing the same original object we see this change
		System.out.println("string1 is equal to alsoString1: " + string1.equals(alsoString1));
		System.out.println("string1 is == to alsoString1: " + (string1==alsoString1));
		
		// using StringBuilder to create mutable character sequences
		StringBuilder stringBuilder1 = new StringBuilder("I'm a string builder object!");
		StringBuilder stringBuilder2 = new StringBuilder("I'm a string builder object!");
		System.out.println(stringBuilder1.hashCode());
		System.out.println(stringBuilder2.hashCode());
		// .equals is actually using a value equality here, not a reference
		// .equals is overridden in the String class so it has a meaningful implementation
		System.out.println(stringBuilder1.equals(stringBuilder2));
		System.out.println(stringBuilder1.toString().equals(stringBuilder2.toString()));
		
		// if we were to convert these StringBuilders to Strings, they would again be 
		// pointing to the same place in the string pool
		System.out.println("stringBuilder1.toString hashcode: " + stringBuilder1.hashCode());
		System.out.println("stringBuilder2.toString hashcode: " + stringBuilder2.hashCode());
		// you cannot cast a StringBuilder to a String - compile time error
		
		// modifying a StringBuilder's value
		StringBuilder alsoStringBuilder1 = stringBuilder1;
		System.out.println("stringBuilder1: " + stringBuilder1);
		System.out.println("hashcode before mutation: " + stringBuilder1.hashCode());
		System.out.println("alsoStringBuilder1: " + alsoStringBuilder1);
		stringBuilder1.append("!");
		// same hashcode because no "new" keyword was used when creating alsoStringBuilder1
		System.out.println("hashcode after mutation: " + stringBuilder1.hashCode());
		System.out.println("alsoStringBuilder1 hashcode after mutation: " + alsoStringBuilder1.hashCode());
		System.out.println(stringBuilder1.toString());
		System.out.println(alsoStringBuilder1.toString());
		
	}

}

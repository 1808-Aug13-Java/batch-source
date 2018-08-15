package com.revature.hierarchy;

import java.io.IOException;

public class AnimalResults extends RuntimeException{  //Here is an unchecked exception
	
	public static void main(String[] args) throws IOException { //Here is the implementation of checked exceptions
		
		//Below are the results for Humans
		Human human = new Human(true, false, true, 2, false); // Polymorphism is employed by adapting the values present in Human.java
															// and AnimalTraits.java to suit the needs of the program.
		
		System.out.println("Are Humans mammals? " + human.isMammal());
		System.out.println("Are Humans birds? " + human.isBird());
		System.out.println("Are Humans Omnivers? " + human.isOmnivore());
		System.out.println("How many legs do Humans have? " + human.getnumLegs());
		System.out.println("Can Humans fly? " + human.getflies());
		
		//Below are the results for Lambs
		Lamb lamb = new Lamb(true, false, false, 4, false); // Polymorphism is employed by adapting the values present in Lamb.java
															// and AnimalTraits.java to suit the needs of the program.
		
		System.out.println("Are Lambs mammals? " + lamb.isMammal());
		System.out.println("Are Lambs birds? " + lamb.isBird());
		System.out.println("Are Lambs Omnivers? " + lamb.isOmnivore());
		System.out.println("How many legs do Lambs have? " + lamb.getnumLegs());
		System.out.println("Can Lambs fly? " + lamb.getflies());
		
		
		//Below are the results for Robins
		Robin robin = new Robin(false, true, false, 2, true);// Polymorphism is employed by adapting the values present in Robins.java
															// and AnimalTraits.java to suit the needs of the program.
		
		System.out.println("Are Robins mammals? " + robin.isMammal());
		System.out.println("Are Robins birds? " + robin.isBird());
		System.out.println("Are Robins Omnivers? " + robin.isOmnivore());
		System.out.println("How many legs do Robins have? " + robin.getnumLegs());
		System.out.println("Can Robins fly? " + robin.getflies());
		
		
	}

}
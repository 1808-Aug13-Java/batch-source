package com.revature.fourpillars;

public interface Animals {
/*The interface Animals, demonstrates abstraction in effect. None of the methods are
 * implemented here other than the default behavior. I do not need to know exactly how 
 * each class implements the methods but I know that they adhere to the method signature 
 * described here.  
 * 
 * It is also polymorphism. Each subclass of Mammals has a slightly different version of 
 * the methods below even though they all have the same method signature.
 */
	
	void heal();
	void eatThis(String foodType) throws NotHungryException;
	void sleep();
	
	default void animalConfirm() {
		System.out.println("You have an animal.");
	}
	
	
}

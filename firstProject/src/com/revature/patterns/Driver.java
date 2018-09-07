package com.revature.patterns;

public class Driver {

	public static void main(String[] args) {
		MySingleton instance = MySingleton.getInstance();
		MySingleton instance1 = MySingleton.getInstance();
		
		System.out.println("instance hash code: " + instance.hashCode());
		System.out.println("instance1 hash code: " + instance1.hashCode());
		
		System.out.println("setting instance value to 5 ");
		instance1.setValue(5);
		System.out.println("getting instance value:");
		System.out.println("instance value: " + instance.getValue());
		
	}
}

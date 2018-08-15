package com.revature.taxonomy;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//Subclass Fish inherits from superclass Mammal
public class Dog extends Mammal{
	public Dog() {
		super();
		numLegs = 4;
		wings = false;
		

		}
	
	public void bark() {
		System.out.println("Woof");
	}
	
	//Implementation of abstract method isSleeping()
	public void isSleeping() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        int hour = Integer.parseInt(sdf.format(cal.getTime()));
        if(hour > 20 | hour < 6)
        	System.out.println("Dog is sleeping.");
        else
        {
        	System.out.println("Dog is awake.");
        }
        
	}
	
	public void getNumLegs() {
		System.out.println("A dog has " + this.numLegs +" legs.");
	}
	//Method overriding is example of Polymorphism
	public void hasWings() {
		System.out.println("A dog has" + (wings != false ? "":" no") + " wings.");;
	}
	//Method overriding is example of Polymorphism
	public void hasVertabrate() {
		System.out.println("A dog has a" + (vertabrate != false ? "":" no") + " vertabrate.");
	}
}

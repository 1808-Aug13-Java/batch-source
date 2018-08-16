package com.revature.animals;


// by extending the class Animal
// we're using the inheritance portion of OOP
// and inheriting some base fields and behavior

public class Mammal extends Animal {
	
	 public static final boolean IS_WARMBLOODED = true;
	 
	 public Mammal() {
		 super();
		 this.setNumberOfExtremities(4);
	 }
	 // by using inheritance I can call the super constructor and set a default number
	 // for mammals extremities value. all mammals have four legs
	 
	 public Mammal(String name) {
		 super(name, 4);
	 }
	 
	 public Mammal(String name, boolean hasFur) {
		 super(name, 4, hasFur);
	 }
	
	
}
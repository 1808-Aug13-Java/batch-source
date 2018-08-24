package com.revature.patterns;

public class AnimalFactory {
	
	public Animal getAnimal(String a) {
		if(a.equals("seal")) {
			return new Seal();
		} else if (a.equals("sloth")) {
			return new Sloth();
		} else if (a.equals("whale")) {
			return new Whale();
		} else {
			return null;
		}
	}

}

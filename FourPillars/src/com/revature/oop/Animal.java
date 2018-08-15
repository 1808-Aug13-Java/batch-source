package com.revature.oop;

import com.revature.exceptions.InvalidLegsException;
import com.revature.exceptions.WrongTerrainException;

public class Animal implements AnimalInterface {   //here is an example of the OOP Pillar: Abstraction
	protected int numLegs;						  //I have an interface which documents what
	protected boolean fly;						 //methods must appear in this class
	protected String sound;
	//these protected variables will help with another pillar down below
	public Animal() {
		super();
	}
	
	public Animal(int numLegs, boolean fly, String sound) {
		this.numLegs = numLegs;
		this.fly = fly;
		this.sound = sound;
	}
	
	/*public Animal(int numLegs, boolean seaCreature, String sound) {
		this.numLegs = numLegs;
		this.seaCreature = seaCreature;
		this.sound = sound;
	}*/
	
	@Override
	public void setNumLegs(int numLegs) {  //By using getters and setters, I am able to 
		this.numLegs = numLegs;			  //circumvent access modifiers and still access the
		if(numLegs <= 0) {				 //protected variables above
			try {						//This is an example of the OOP Pillar: Encapsulation
				throw new InvalidLegsException("This animal must have at least one leg"); 
			} catch (InvalidLegsException e) { //This is my custom exception which is thrown
				e.printStackTrace();		  //if particular animals are set to have 0 legs
			}
		}
	}
	
	public int getNumLegs() {
		return this.numLegs;
	}
	
	public void setFly(boolean fly) {
		this.fly = fly;
		if(fly) {
			try {
				throw new WrongTerrainException("This animal cannot fly");
			} catch (WrongTerrainException e) { //This is my other custom exception which is
				e.printStackTrace();		   //thrown if an animal cannot fly but is set to true
			}
		}
	}
	
	public boolean getFly() {
		return this.fly;
	}
	
	public void setSound(String sound) {
		this.sound = sound;
	}
	
	public String getSound() {
		return this.sound;
	}
}

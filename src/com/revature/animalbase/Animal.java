package com.revature.animalbase;

import com.revature.exceptions.AccelerationException;
import com.revature.exceptions.NegativeLegsException;

public abstract class Animal {
	/*By utilizing the protected access modifier, I demonstrate encapsulation
	 *through restricting access to the variables numberLegs, mammal, speed, and name.
	 */
	protected int numberLegs;
	protected boolean mammal;
	protected int speed;
	protected String name;
	public abstract void walk();
	/*Abstraction is displayed here 
	 *because the walk method is
	  defined in the child class.*/
	//Utilizing the private access modifier allows me to count the number of animals here
	//by incrementing +1 whenever the Animal constructor is called and at no other time.
	private static int numberOfAnimals = 0;
	public Animal() {
		super();
	}
	public Animal(int legs, boolean mammal, int speed, String name) {
		this.numberLegs = legs;
		this.mammal = mammal;
		this.speed = speed;
		this.name = name;
		numberOfAnimals++;
	}
	
	public void eat() {
		System.out.println("The " + name +" is eating.");
	}

	public static int getNumberOfAnimals() {
		return numberOfAnimals;
	}
	public int getNumberLegs() {
		return numberLegs;
	}

	public void setNumberLegs(int numberLegs) {
		if(numberLegs<0) {
			try {
				throw new NegativeLegsException("Animals can't have negative legs!");
			}catch (NegativeLegsException e) {
				e.printStackTrace();
			}
		}else {
			this.numberLegs = numberLegs;
		}
	}

	public boolean isMammal() {
		return mammal;
	}

	public void setMammal(boolean mammal) {
		this.mammal = mammal;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void speedChange(float accelFactor) {
		if(accelFactor == 0) {
			try {
				throw new AccelerationException("Animals can't go infinite speed.");
			}catch (AccelerationException e) {
				e.printStackTrace();
			}
		}else {
			float calc = ((float)speed)/accelFactor;
			setSpeed((int)calc);
		}
	}
}

package com.revature.fourpillars;

public class Dog extends Animal {
	int exerciseTime;

	public Dog() {}
	public Dog(int numLeg, String sex, int eTime) //example of inheritance. numlegs and sex from Animal
	{
		this.numLegs = numLeg;
		this.sex = sex;
		this.exerciseTime = eTime;
	}	//constructor
	
	@Override							//Example of Polymorphism because we're overriding the
	public void displayInfo()		//abstract method from the superclass Animal and filling out a 
	{								//implementation specific to Dog class
		System.out.println("Sex, number of legs, and exerciseTime: " + sex+", "+numLegs+", "+exerciseTime);
	}
	
	public int getExerciseTime() {			//getter
		return exerciseTime;
	}

	public void setExerciseTime(int exerciseTime) {	//setter
		this.exerciseTime = exerciseTime;
	}
	
	
	
}

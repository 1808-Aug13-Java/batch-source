package com.revature.fourpillars;

public class Tiger extends Mammals implements Animals {
	
	private static final int TIG_LEGS=4;
	public Tiger() {
		super();
		this.setNumLegs(TIG_LEGS);
		}
	public Tiger(boolean isInjured, boolean isHungry ) {
		super(TIG_LEGS, isInjured, isHungry);	
		//here the parameterized constructor of Mammals() is called instead
		}
	//begin interface implementation
public void heal() {
	if ( this.isInjured()) {
		this.setInjured(false);
		this.setHungry(false); //the tiger implementation of the heal method of 
		//the animal interface also feeds the tiger regardless of whether he is hungry or not
	System.out.println("This tiger is now healed and fed");
	}
	else {
		System.out.println("This tiger is isn't injured");
	}
}
public void eatThis(String foodType) {
	
	System.out.println("This tiger will eat "+ foodType);
	this.setHungry(false);
	}

public void sleep() {
		//could be a runtime exception
		if (this.isHungry()) {
				
				System.out.println("Tiger is still hungry and cannot sleep");
		}
		else {
			 System.out.println("Tiger is not hungry and can sleep"); 
			 this.setSleeping(true);
		}
		
	}
}



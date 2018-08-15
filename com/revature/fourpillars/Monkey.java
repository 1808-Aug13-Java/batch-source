package com.revature.fourpillars;

public class Monkey extends Mammals implements Animals{

	public static final int MK_LEGS=2;
	
	public Monkey () {
		super();
		this.setNumLegs(MK_LEGS);
	}
	
	public Monkey(boolean isInjured, boolean isHungry) {
		super(MK_LEGS, isInjured, isHungry);	
	}
	
	//begin interface implementation
	public void heal() {
		if (this.isInjured()) {
			this.setInjured(false);
			this.setHungry(false); 
			this.setSleeping(true);
			//the Monkey implementation of the heal method of 
			//the animal interface also feeds the Monkey regardless of whether he is hungry or not
			//and puts it to sleep as well
		System.out.println("This Monkey is now healed, fed, and sleeping");
		}
		else {
			System.out.println("This Monkey is isn't injured");
		}
	}
	public void eatThis(String foodType) {
		
		System.out.println("This Monkey will eat "+ foodType);
		this.setHungry(false);
		}

	public void sleep() {
			//could be a runtime exception
			if (this.isHungry()) {
					
					System.out.println("Monkey is still hungry and cannot sleep");
			}
			else {
				 System.out.println("Monkey is not hungry and can sleep"); 
				 this.setSleeping(true);
			}
			
		}
}

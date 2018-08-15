package com.revature.animals;

import com.revature.exceptions.NoVocalizationException;

// The extends keyword denotes that we are practicing Inheritance by subclassing/extending "Animals"
public class Fish extends Animals {
	
	// These properties are securely encapsulated through the use of the private access modifier
	// This is an example of the Encapsulation pillar, as we are effectively hiding/securing the
	// data until it is accessed via getter/setter methods
	private String saltwaterOrFreshwater;
	private boolean tasty;
	
	public Fish() {
		super();
	}
	
	
	public Fish(boolean hasLegs, String vocalizationType, String movementStyle) {
		super(hasLegs, vocalizationType, movementStyle);
	}
	
	// all setXY below are setter methods
	// all getXY below are getter methods
	public void setSaltwaterOrFreshwater(String saltwaterOrFreshwater) {
		this.saltwaterOrFreshwater = saltwaterOrFreshwater;
	}
	
	public String getSaltwaterOrFreshwater() {
		return saltwaterOrFreshwater;
	}
	
	public void setTasty(boolean tasty) {
		this.tasty = tasty;
	}
	
	public boolean getTasty() {
		return tasty;
	}
	
	// setVocalizationType is overridden in order to throw an error message using a custom
	// NoVocalizationException method to print an error message to the console if
	// the vocalizationType is equal to "None" (e.g., in Fish)
	@Override
	public void setVocalizationType(String vocalizationType) {
		if (vocalizationType == "None") {
			try {
				throw new NoVocalizationException("Hmm, it looks like this animal doesn't make a noise.");
			} catch (NoVocalizationException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Fish vocalization type is: " + vocalizationType);
			this.vocalizationType = vocalizationType;
		}
	}
}

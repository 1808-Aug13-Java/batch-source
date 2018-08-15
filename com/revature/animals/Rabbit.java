package com.revature.animals;

public class Rabbit extends AnimalBase{
	
	// Encapsulation
	// Private Access modifiers
	private int jumpHeight;
	private String furColor;
	
	// Constructors
	public Rabbit() {
		super();
	}

	public Rabbit(int numApendages, int landSpeed, boolean hasTail) {
		super(numApendages, landSpeed, hasTail);
	}

	// Getters and Setters
	public int getJumpHeight() {
		return jumpHeight;
	}

	public void setJumpHeight(int jumpHeight) {
		this.jumpHeight = jumpHeight;
	}

	public String getFurColor() {
		return furColor;
	}

	public void setFurColor(String furColor) {
		this.furColor = furColor;
	}
}

package com.revature.animals;

public class Turtle extends AnimalBase implements AquaticInterface{

	// Encapsulation - Private access modifier
	private int shellHardness;
	private int shellSize;
	
	// Constructors
	public Turtle() {
		super();
	}

	public Turtle(int numApendages, int landSpeed, boolean hasTail) {
		super(numApendages, landSpeed, hasTail);
	}

	// Polymorphism - Method Overloading
	public Turtle(int shellHardness, int shellSize) {
		super();
		this.shellHardness = shellHardness;
		this.shellSize = shellSize;
	}

	// Getters and Setters
	public int getShellHardness() {
		return shellHardness;
	}

	public void setShellHardness(int shellHardness) {
		this.shellHardness = shellHardness;
	}

	public int getShellSize() {
		return shellSize;
	}

	public void setShellSize(int shellSize) {
		this.shellSize = shellSize;
	}

	// Polymorphism - Method Overriding
	@Override
	public void swim() {
		System.out.println("Turtle is swimming");
	}

}

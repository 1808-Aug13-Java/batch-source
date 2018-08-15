package com.revature.fourPillars;

public class Liopleurodon extends Reptile {

	public Liopleurodon () {
		this.name("Magical Liopleurodon");
		this.isAquatic = true;
		this.hasScales = false;
	}
	
	public Liopleurodon(String name) {
		name(name);
		isAquatic = true;
		hasScales = false;
	}
	
	@Override
	public String speak() {
		System.out.println("~Underwater Screaching~");
		return "~Underwater Screaching~";
	}

	@Override
	public void eat() {
		System.out.println("~" + name + " Consumes 1,000 lbs of food.~");
	}

	@Override
	public Animal mate() {
		System.out.println("~Live births another Liopleurodon underwater~");
		Liopleurodon baby = new Liopleurodon();
		return baby;
	}

	@Override
	public void kill() throws GuiltOfConscienceException{
		this.isAlive = false;
		throw new GuiltOfConscienceException("You can't really kill " + name + ", can you?");
	}
	
	public void come() throws InabilityToPerformActionException {
		throw new InabilityToPerformActionException(name + " has been extinct for thousands of years.");
	}

	@Override
	public String toString() {
		String msg = "";
		msg += "Name: " + name;
		msg += " is alive: " + isAlive;
		msg += " is aquatic: " + isAquatic;
		msg += " has scales: " + hasScales;
		msg += " but is unfortunately extinct.";
		return msg;
	}
}

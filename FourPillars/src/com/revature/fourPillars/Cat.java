package com.revature.fourPillars;

public class Cat extends Mammal {

	private int lives = 9;
	
	public Cat() {
		name = "Cheshire";
		numberOfLegs = 4;
	}
	
	public Cat(String n, int l) {
		name = n;
		numberOfLegs = l;
	}
	
	@Override
	public void shed() {
		System.out.println("Hair is literally everywhere.");
	}

	@Override
	public String speak() {
		System.out.println("~Mrrrooooooowwwwwww~");
		return "~Mrrrooooooowwwwwww~";
	}

	@Override
	public void eat() {
		System.out.println("~" + name + " eats the food. Throws it up in your shoe.~");
	}

	@Override
	public Animal mate() {
		System.out.println("~" + name + " makes 2 dozen kittens with the neighbor cats.~");
		Cat baby = new Cat();
		return baby;
	}
	
	public void come() throws InabilityToPerformActionException {
		throw new InabilityToPerformActionException("Cats don't listen.");
	}

	@Override
	public void kill() throws GuiltOfConscienceException{
		if (--lives <= 0)
		{	
			this.isAlive = false;
			throw new GuiltOfConscienceException("You can't really kill " + name + ", can you?");
		}
		else
			System.out.println("~" + name + " managed to escape, with " + lives + " left.~");
	}
	
	@Override
	public String toString() {
		String msg = "Name: " + name;
		msg += " is alive: " + isAlive;
		msg += " has " + numberOfLegs + " legs";
		msg += " has " + lives + " lives left";
		msg += " and wants food again.";
		return msg;
	}
}

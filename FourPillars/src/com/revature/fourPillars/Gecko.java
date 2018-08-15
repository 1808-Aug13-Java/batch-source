package com.revature.fourPillars;

public class Gecko extends Reptile {

	private int limbs = 5;
	
	public Gecko() {
		name = "Geico Gecko";
		isAquatic = false;
		hasScales = false;
	}
	
	public Gecko(String n) {
		name = n;
		isAquatic = false;
		hasScales = false;
	}
	
	public Gecko(String n, boolean a, boolean h) {
		name = n;
		isAquatic = a;
		hasScales = h;
	}
	
	@Override
	public String speak() {
		System.out.println("~Hiiisssssssss~");
		return "~Hiiissssssss~";
	}

	@Override
	public void eat() {
		System.out.println("~" + name + " eats 3 oz of food.~");
	}

	@Override
	public Animal mate() {
		System.out.println("~Lays a few dozen eggs in the dirt.~");
		Gecko baby = new Gecko();
		return baby;
	}
	
	public void loseLimb() throws NotEnoughLegsException{
		if (limbs <= 0)
			throw new NotEnoughLegsException(name + " doesn't have any more limbs!");
		else
			limbs--;
	}
	public void regrowLimb() {
		if (limbs < 5)
			limbs++;
	}

	@Override
	public void kill() throws GuiltOfConscienceException{
		this.isAlive = false;
		throw new GuiltOfConscienceException("You can't really kill " + name + ", can you?");
	}
	
	public void come() {
		System.out.println("~" + name + " climbs up your arm.~");;
	}

	@Override
	public String toString() {
		String msg = "Name: " + name;
		msg += " is alive: " + isAlive;
		msg += " is aquatic: " + isAquatic;
		msg += " has scales: " + hasScales;
		msg += " and has " + limbs + " limbs left.";
		return msg;
	}
}

package com.revature.fourPillars;

public class Dog extends Mammal {

	public Dog() {
		name = "Doug";
		numberOfLegs = 4;
	}
	
	public Dog(String n, int l) {
		name = n;
		numberOfLegs = l;
	}
	
	@Override
	public void shed() {
		System.out.println("~" + name + " sheds a bit on the couch.~");
	}

	@Override
	public String speak() {
		System.out.println("~BOOF!~");
		return "~BOOF!~";
	}

	@Override
	public void eat() {
		System.out.println("~" + name + " sloppily eats kibble.~");
	}

	@Override
	public Animal mate() {
		System.out.println("~Happily makes many pups.~");
		Dog pup = new Dog();
		return pup;
	}

	public void sit() {
		System.out.println("~" + name + " happily sits.~");
	}
	
	public void stay() {
		System.out.println("~" + name + " happily stays.~");
	}
	
	public void come() {
		System.out.println("~" + name + " excitedly rushes over.~");
	}

	@Override
	public void kill() throws GuiltOfConscienceException{
		this.isAlive = false;
		throw new GuiltOfConscienceException("You can't really kill " + name + ", can you?");
	}
	
	@Override
	public String toString() {
		String msg = "Name: " + name;
		msg += " is alive: " + isAlive;
		msg += " has " + numberOfLegs + " legs";
		msg += " and is super excited.";
		return msg;
	}
}

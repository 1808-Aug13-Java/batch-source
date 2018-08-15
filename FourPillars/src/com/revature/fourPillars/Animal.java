package com.revature.fourPillars;

public abstract class Animal {

	protected boolean isAlive = true;
	protected String name;
	
	//abstracting the required processes for propagation of life
	public abstract String speak();
	public abstract void eat();
	public abstract Animal mate();
	public abstract void come();
	public abstract void kill() throws GuiltOfConscienceException;
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void name(String n) {
		name = n;
	}
	
	public String name() {
		return name;
	}
}

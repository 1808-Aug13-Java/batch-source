package com.revature.beans;

public class BearAutowiringByName extends Bear {

	//uses setter injection based on the name of the setter method 
	//corresponding to the name of any of our beans
	public void setCave(Cave cave) {
		this.cave = cave;
	}
	
	@Override
	public String toString() {
		return "BearAutowiringByName [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}
	
	@Override
	public void methodInBear() {
		System.out.println("Method in BearAutowiringByName called. Bear is: "+toString());
	}

}

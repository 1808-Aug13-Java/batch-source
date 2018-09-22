package com.revature.beans;

public class BearAutowiringByType extends Bear {


	//uses setter injection based on the name of the variable 
	//corresponding to the type of any of our beans
	public void setSomethingElse(Cave cave) {
		this.cave = cave;
	}

	@Override
	public String toString() {
		return "BearAutowiringByType [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}
	
	
	@Override 
	public void methodInBear() {
		System.out.println("method in BearAutowiringByType called. this bear is: "+toString());
	}
}

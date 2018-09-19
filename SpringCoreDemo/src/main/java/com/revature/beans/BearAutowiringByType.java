package com.revature.beans;

public class BearAutowiringByType extends Bear {

	
	public void setCave(Cave cave) {
		this.cave = cave;
	}
	
	
	@Override
	public String toString() {
		return "BearAutowiringByType [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}

	@Override
	public void methodInBear() {
		System.out.println("Method in BearAutowiringByType. this bear is: " + toString());
	}
}

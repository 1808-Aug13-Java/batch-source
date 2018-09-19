package com.revature.beans;

public class BearAutowiringByName extends Bear{

	@Override
	public void setCave(Cave cave) {
		this.cave = cave;
	}
	

	@Override
	public String toString() {
		return "BearAutowiringByName [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}
	
	@Override
	public void methodInBear() {
		System.out.println("Method in BearAutowiringByName. this bear is: " + toString());
	}
	
	

}

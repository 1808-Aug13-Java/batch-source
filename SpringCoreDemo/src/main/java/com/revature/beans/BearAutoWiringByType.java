package com.revature.beans;

public class BearAutoWiringByType extends Bear{

	
	@Override
	public void setCave(Cave cave) {
		this.cave = cave;
	}
	@Override
	public String toString() {
		return "BearAutoWiringByType [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}

	@Override
	public void methodInBear() {
		System.out.println("bear in autowiringbyType" + toString());
	}
	
	
}

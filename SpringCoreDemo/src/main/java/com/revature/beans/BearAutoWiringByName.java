package com.revature.beans;

public class BearAutoWiringByName extends Bear{

	@Override
	public String toString() {
		return "BearAutoWriringByName [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}

	@Override
	public void setCave(Cave cave) {
		this.cave = cave;
	}
	@Override
	public void methodInBear() {
		System.out.println("bear in autowiring" + toString());
	}
	
	
}

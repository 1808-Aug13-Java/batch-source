package com.revature.fourpillars;

public interface Amphibian {
	
	//variables in an interface are public, static, and final. A frog will always be an amphibian
	String classification = "amphibian";
	
	//the methods in an interface are public by default, and will be implemented by the class implementing the interface
	void breathUnderWater();
	void setBodyTemp();
	
}

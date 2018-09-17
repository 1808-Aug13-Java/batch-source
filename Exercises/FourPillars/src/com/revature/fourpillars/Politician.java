package com.revature.fourpillars;

// Abstraction: hiding/focusing less on implementation details
public class Politician extends Celebrity {
	// Encapsulation: making the instance field private in order to make the code more robust
	private boolean isCorrupt;
	private String stateOfResidence;

	public Politician() {
		isCorrupt = false;
		setStateOfResidence("");
	}

	public boolean isCorrupt() {
		return isCorrupt;
	}

	public void setCorrupt(boolean isCorrupt) {
		this.isCorrupt = isCorrupt;
	}

	public String getStateOfResidence() {
		return stateOfResidence;
	}

	public void setStateOfResidence(String stateOfResidence) {
		this.stateOfResidence = stateOfResidence;
	}
}
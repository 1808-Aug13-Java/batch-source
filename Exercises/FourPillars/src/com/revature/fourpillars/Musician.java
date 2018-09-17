package com.revature.fourpillars;

public class Musician extends Celebrity {
	private String instruments[]; // unintended pun

	public Musician(String ...instruments) {
		super();
		this.instruments = instruments;
	}

	public String[] getInstruments() {
		return instruments;
	}

	public void setInstruments(String[] instruments) {
		this.instruments = instruments;
	}
}

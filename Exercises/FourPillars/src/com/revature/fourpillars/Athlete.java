package com.revature.fourpillars;

public class Athlete extends Celebrity {
	private String sport;
	private int numWins;
	private int numLosses;

	public Athlete() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Athlete(String profession, String name, int age, String sport) {
		super(profession, name, age);
		this.setSport(sport);
	}
	
	public float getWinPercentage() {
		return ((float) numWins)/(numWins + numLosses);
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public int getNumWins() {
		return numWins;
	}

	public void setNumWins(int numWins) {
		this.numWins = numWins;
	}

	public int getNumLosses() {
		return numLosses;
	}

	public void setNumLosses(int numLosses) {
		this.numLosses = numLosses;
	}	
}

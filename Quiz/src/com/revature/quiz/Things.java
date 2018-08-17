package com.revature.quiz;

public class Things implements Comparable<Things>{

	private String qOrP;
	public Things(String s) {
		qOrP = s;
	}
	public String getContents() {
		return qOrP;
	}
	@Override
	public int compareTo(Things o2) {
		int rando = (int)(Math.random()*1000) - (int)(Math.random()*1000);
		return rando;
	}

}

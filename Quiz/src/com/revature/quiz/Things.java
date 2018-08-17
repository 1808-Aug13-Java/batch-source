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
		int hash = this.qOrP.hashCode() - o2.getContents().hashCode();
		return hash;
	}

}

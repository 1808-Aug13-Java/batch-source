package com.revature.collections;

import java.util.Comparator;

public class CompareSize implements Comparator<Pizza> {

	@Override
	public int compare(Pizza arg0, Pizza arg1) {
		return arg0.getDiameter() - arg1.getDiameter();
	}
	
}

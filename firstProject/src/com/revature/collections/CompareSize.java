package com.revature.collections;

import java.util.Comparator;

public class CompareSize implements Comparator<Pizza>{

	@Override
	public int compare(Pizza o1, Pizza o2) {

		return o1.getDiameter() - o2.getDiameter();
	}

}

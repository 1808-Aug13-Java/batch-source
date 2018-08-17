package com.revature.game;

import java.util.ArrayList;
import java.util.Scanner;

public class People {
	
	private ArrayList<String> names;
//	private static Scanner sc = new Scanner(System.in);
	
	public People() {
		super();
	}

	public People(ArrayList<String> names) {
		super();
		this.names = names;
	}

	public String getName() {
		int size = names.size();
		if (size == 0) {
//			System.out.println("No more names");
			return null;
		}
		int index = (int) (Math.random()*1500) % size;
		String name = names.get(index);
		names.remove(index);
		names.trimToSize();
		return name;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}
}

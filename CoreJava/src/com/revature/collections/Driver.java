package com.revature.collections;

import java.util.LinkedHashSet;
import java.util.TreeSet;

public class Driver {

	public static void main(String[] args) {

		LinkedHashSet<Integer> hSet = new LinkedHashSet<Integer>();
		hSet.add(6);
		hSet.add(2);
		hSet.add(3);
		hSet.add(4);
		hSet.add(6);
		hSet.add(6);
		System.out.println(hSet);
		System.out.println();
		
		TreeSet<Integer> tSet = new TreeSet<Integer>();
		tSet.add(6);
		tSet.add(2);
		tSet.add(3);
		tSet.add(4);
		tSet.add(6);
		tSet.add(6);
		System.out.println(tSet);
		System.out.println();
		
		TreeSet<String> sSet = new TreeSet<String>();
		sSet.add("charlie");
		sSet.add("bravo");
		sSet.add("alpha");
		System.out.println(sSet);
		sSet.forEach((item)->{
			System.out.println("item: "+item);
			
		});
		System.out.println(sSet);
		System.out.println();

    LinkedHash<Pizza> plist = new LinkedList<Pizza>;
		
	}

}

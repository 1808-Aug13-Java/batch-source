package com.revature.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class Driver {

	public static void main(String[] args) {
		
		LinkedHashSet<Integer> hSet = new LinkedHashSet<Integer>();
		hSet.add(6);
		hSet.add(7);
		hSet.add(7);
		hSet.add(4);
		System.out.println("HashSet: " + hSet);
		
		TreeSet<Integer> tSet = new TreeSet<Integer>();
		tSet.add(6);
		tSet.add(7);
		tSet.add(7);
		tSet.add(4);
		System.out.println("TreeSet: " + tSet);
		
		TreeSet<String> strTSet = new TreeSet<String>();
		strTSet.add("Hello");
		strTSet.add("Bean");
		strTSet.add("Alpha");
		System.out.println("String tree set: " + strTSet);
		
		LinkedHashSet<Pizza> foodSet = new LinkedHashSet<Pizza>();
		String[] t1 = {"cheese", "spinach"};
		String[] t2 = {"pepperoni", "sausage", "spinach"};
		String[] t3 = {"mushrooms", "pineapples", "bacon"};
		foodSet.add(new Pizza(t1, 8, 20, "thin crust"));
		foodSet.add(new Pizza(t2, 10, 24, "deep dish"));
		foodSet.add(new Pizza(t3, 6, 16, "thin crust"));
		for(Pizza pizza : foodSet) {
			System.out.println(pizza);
		}
		System.out.println();
		TreeSet<Pizza> foodTreeSet = new TreeSet<Pizza>();
		foodTreeSet.add(new Pizza(t1, 8, 20, "thin crust"));
		foodTreeSet.add(new Pizza(t2, 10, 24, "deep dish"));
		foodTreeSet.add(new Pizza(t3, 6, 16, "thin crust"));
		foodTreeSet.add(new Pizza(t3, 6, 16, "thin crust"));
		for(Pizza pizza : foodTreeSet) {
			System.out.println(pizza);
		}
		System.out.println();
		
		// implements queue and list
		// can contain duplicates
		System.out.println("Before using utility class' sort method:");
		LinkedList<Pizza> foodList = new LinkedList<Pizza>();
		foodList.add(new Pizza(t1, 8, 20, "thin crust"));
		foodList.add(new Pizza(t2, 10, 24, "deep dish"));
		foodList.add(new Pizza(t3, 6, 16, "thin crust"));
		foodList.add(new Pizza(t2, 10, 24, "deep dish"));
		
		for(Pizza p : foodList) {
			System.out.println(p);
		}
		
		System.out.println();
		System.out.println("AFter using utility class' sort method:");
		Collections.sort(foodList);
		
		for(Pizza p : foodList) {
			System.out.println(p);
		}
		System.out.println();
		
		System.out.println("Using utility class' sort method witha  comparator (diameter)");
		Collections.sort(foodList, new CompareSize());
		for(Pizza p : foodList) {
			System.out.println(p);
		}
		
		System.out.println();
		System.out.println("Using a lambda in our forEach method: ");
		foodList.forEach((pizza)-> {
			System.out.println(pizza);
		});
		
		Comparator<Pizza> compareToppings = 
				(p1, p2) -> p1.getToppings().length - p2.getToppings().length;
		
		System.out.println();
		System.out.println("Sorting our list using a comparator defined with a lambda");
		Collections.sort(foodList, compareToppings);
		foodList.forEach((p) -> { System.out.println(p); });
		
		
	}
	
}

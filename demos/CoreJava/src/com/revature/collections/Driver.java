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
		hSet.add(2);
		hSet.add(3);
		hSet.add(4);
		hSet.add(6);
		hSet.add(6);
//		System.out.println(hSet);
//		System.out.println();
		
		TreeSet<Integer> tSet = new TreeSet<Integer>();
		tSet.add(6);
		tSet.add(2);
		tSet.add(3);
		tSet.add(4);
		tSet.add(6);
		tSet.add(6);
//		System.out.println(tSet);
//		System.out.println();
		
		TreeSet<String> sSet = new TreeSet<String>();
		sSet.add("charlie");
		sSet.add("bravo");
		sSet.add("alpha");
//		System.out.println(sSet);
//		System.out.println();
		
		LinkedHashSet<Pizza> foodSet = new LinkedHashSet<Pizza>();
		String[] t1 = {"pepperoni","sausage","spinach"};
		foodSet.add(new Pizza(t1,8,24,"thin crust"));
		String[] t2 = {"cheese"};
		foodSet.add(new Pizza(t2,6,16,"deep dish"));
		String[] t3 = {"mushrooms", "bacon"};
		foodSet.add(new Pizza(t3,9,12,"Sicilian"));
		foodSet.add(new Pizza(t2,10,18,"thin crust"));
		for(Pizza p : foodSet) {
			System.out.println(p);
		}
		System.out.println();
		
		TreeSet<Pizza> foodTreeSet = new TreeSet<Pizza>();
		foodTreeSet.add(new Pizza(t1,8,24,"thin crust"));
		foodTreeSet.add(new Pizza(t2,6,16,"deep dish"));
		foodTreeSet.add(new Pizza(t3,9,12,"Sicilian"));
		foodTreeSet.add(new Pizza(t2,10,18,"thin crust"));
		for(Pizza p : foodTreeSet) {
			System.out.println(p);
		}
		System.out.println();
		
		System.out.println("Before using utility class's sort method:");
		LinkedList<Pizza> foodList = new LinkedList<Pizza>();
		foodList.add(new Pizza(t1,8,24,"thin crust"));
		foodList.add(new Pizza(t2,6,16,"deep dish"));
		foodList.add(new Pizza(t3,9,12,"Sicilian"));
		foodList.add(new Pizza(t2,10,18,"thin crust"));
		for(Pizza p : foodList) {
			System.out.println(p);
		}
		System.out.println();
		
		System.out.println("After using utility class's sort method:");
		Collections.sort(foodList);
		for(Pizza p : foodList) {
			System.out.println(p);
		}
		System.out.println();
		
		System.out.println("Using utility class's sort method with a comparator object:");
		Comparator<Pizza> compareSize = new CompareSize();
		Collections.sort(foodList, compareSize);
		for(Pizza p: foodList) {
			System.out.println(p);
		}
		System.out.println();
		
		System.out.println("Using a lambda in our forEach method:");
		foodList.forEach((i)->{
			System.out.println(i);
		});
		
//		Comparator<Pizza> compareToppings =
//				(Pizza p1, Pizza p2) -> {
//					return p1.getToppings().length-p2.getToppings().length;
//				};
		
		Comparator<Pizza> compareToppings = 
				(p1, p2) -> p1.getToppings().length-p2.getToppings().length;
		
		System.out.println();
<<<<<<< HEAD

    LinkedHash<Pizza> plist = new LinkedList<Pizza>;
=======
		System.out.println("Sorting our list using a Comparator defined with a lambda");
		Collections.sort(foodList, compareToppings);
		for(Pizza p : foodList) {
			System.out.println(p);
		}
>>>>>>> 86d877de8c73670cbda975031f575b3ad4b50893
		
	}

}

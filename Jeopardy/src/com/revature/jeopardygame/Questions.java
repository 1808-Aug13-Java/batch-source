package com.revature.jeopardygame;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Questions {

	private static Scanner sc= new Scanner(System.in);

	
	
	
	LinkedHashMap<Integer,String> questions= new LinkedHashMap<Integer,String>();
	LinkedHashMap<Integer,String> persons= new LinkedHashMap<Integer,String>();
	
	Random randq= new Random();
	
	int[] questionNums= new int[10];
			
	public Questions() {
		super();
	}
	
	public Questions(String [] qs, String [] ps) {
		super();
		for(int i=0;i<10;i++) {
			questions.put(i,qs[i]);
		}
		for(int i=0;i<10;i++) {
			persons.put(i,ps[i]);
		}
	}
	
	public void run() {
		/*
		int[] questionNums= new int[10];
		int[] personIndex=new int[10];
		LinkedList<Integer> perIndex=new LinkedList<Integer>();
		LinkedList<Integer> qsIndex=new LinkedList<Integer>();
		*/
		Integer cool;
		System.out.println("Hi, enter 1 to randomize or 2 to print");
		cool=sc.nextInt();
		
		switch(cool) {
		
		
		
	}
	}//end run
	
	public LinkedList randomize() {
		Random randq= new Random();
		
		int[] questionNums= new int[10];
		int[] personIndex=new int[10];
		LinkedList<Integer> pIndex=new LinkedList<Integer>();
		int counter=10;
		int tester;
		
		for(int i=0; i<10;i++) {
			
			tester=randq.nextInt(counter);
			while(pIndex.contains(tester)) {
				tester=randq.nextInt(counter);
			}
			pIndex.add(tester);
			counter--;
			
		}
		
		return pIndex;
	}
	
	public String getQuestion(Integer i) {
		return questions.get(i);
	}

	public String getPerson(Integer i) {
		return persons.get(i);
	}
	

}




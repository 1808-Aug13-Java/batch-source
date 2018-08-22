package com.revature.jeopardygame;

import java.util.LinkedList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	create  scanner instance
		
		
		String[] qs= {"a","b","c","d","e","f","g","h","w","r"};
		String[] ps= {"bob", "jim","mary", "rob","james","bro","asd","rew","pop","thu" };
	
		int[] questionNums= new int[10];
		int[] personIndex=new int[10];
		LinkedList<Integer> perIndex=new LinkedList<Integer>();
		LinkedList<Integer> qsIndex=new LinkedList<Integer>();
		Integer cool=0;
		Questions worker= new Questions(qs,ps);
		
		
		
		switch (cool) {
		case 1:{
			perIndex=worker.randomize();
			qsIndex=worker.randomize();
			System.out.println(perIndex);
			System.out.println(qsIndex);
		}
		case 2:{
			
			//worker.getPerson(i);
			//worker.getQuestion(i)
			
		}
		default: 
		
		}
		
	}

}

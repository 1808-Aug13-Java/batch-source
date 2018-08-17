package com.revature.collections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GameDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("For future reference: Please press ENTER to move to next \n");
		String moveNext;
		
		LinkedList<Game> nameList = new LinkedList<Game>(); // Does LinkedHashSet doesn't allow duplicates
		
//		String[] names;
//		String[] questions;
		
		String path1 = "src/com/revature/collections/name_data.txt";
		String path2 = "src/com/revature/collections/question_data.txt";
		
		// create a buffered reader
		try {
			BufferedReader br = new BufferedReader(new FileReader(path1));
			String line = br.readLine();
			
			BufferedReader br2 = new BufferedReader(new FileReader(path2));
			String line2 = br2.readLine();
			
			while(line != null) {
				line = br.readLine();
				
				line2 = br2.readLine();
				
				nameList.add(new Game(line,line2));
			}
			br.close();
			br2.close();
		} catch (IOException e) {//FileNotFound is an IOException
			e.printStackTrace();
		}
		
		Collections.shuffle(nameList);
		
		for(Game g : nameList) {
			System.out.println(g); // print out each name + question
			System.out.println("Please press ENTER to move to next");
			moveNext = sc.nextLine();
		}
		System.out.println("Game over");
	}
	
	private int getRandom() {
		Random random = new Random();
		int index = random.nextInt(28);
		
		return index;
	}
}

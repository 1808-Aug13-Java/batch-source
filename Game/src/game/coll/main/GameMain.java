package game.coll.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class GameMain {

	public static void main(String[] args) 
	{
		
		LinkedList<String> questions = new LinkedList<>();
		LinkedList<String> people = new LinkedList<>();
		
		// Add The contents
		
		people.add("Lord Trooper");
		people.add("Kylo Ren");
		people.add("Bill Gates");
		people.add("Ronald Weasley");
		
		questions.add("What is your name?");
		questions.add("What is your quest?");
		questions.add("What is your favorite color?");
		questions.add("What is the capital of Assyria?");
		
		Scanner scanny = new Scanner(System.in);
		
		System.out.println("Please enter a file name for the contestants:");
		String contestants = scanny.nextLine();
		System.out.println("Please enter a file name for the questions:");
		String questionFile = scanny.nextLine();
		
		try
		{
			File qFile = new File(questionFile);
			File pFile = new File(contestants);
			Scanner qScan = new Scanner(qFile);
			Scanner pScan = new Scanner(pFile);
			
			while(qScan.hasNextLine())
				questions.add(qScan.nextLine());
			
			while(pScan.hasNextLine())
				people.add(pScan.nextLine());
			
			pScan.close();
			qScan.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File Not found: " + e.getMessage());
		}
		
		
		
		// Randomize
		RandomSorter rs = new RandomSorter();
		Collections.sort(questions, rs);
		Collections.sort(people, rs);
		

		
		System.out.println("Press [enter] to go through the list!");
		for(int rust = 0; rust < questions.size() && rust < people.size(); rust++)
		{
			scanny.nextLine();
			System.out.println(people.get(rust) + ": " + questions.get(rust));
		}
		
		scanny.close();
	}

}

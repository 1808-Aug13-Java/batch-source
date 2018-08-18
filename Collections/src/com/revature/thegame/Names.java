package com.revature.thegame;

import java.util.ArrayList;
import java.util.Collections;

public class Names {
	
	
	ArrayList<String> nameList = new ArrayList<String>();
	public void generateQuestions() {
		nameList.add("Jerry Kim");
		nameList.add("Jonnathan Novoa");
		nameList.add("Alexander Huerta");
		nameList.add("Jonathan Gonzalez Davila");
		nameList.add("Henry Saing");
		nameList.add("Austen Schips");
		nameList.add("Chinonso Chukwuma");
		nameList.add("Pranav Udayakumar");
		nameList.add("Crandon Riordan");
		nameList.add("Maxwell Mooney");
		nameList.add("Jorge Sagrero-Perez");
		nameList.add("Charles Crandall");
		nameList.add("Martin Smallwood");
		nameList.add("Chandrika Sinha");
		nameList.add("David Calkins");
		nameList.add("Jan Balangue");
		nameList.add("John Jacko");
		nameList.add("Alex Bumpers");
		nameList.add("Julie Seals");
		nameList.add("Cirey Francis");
		nameList.add("Boubacar Diallo");
		nameList.add("Cindy Peng");
		nameList.add("Brian Simmons");
		nameList.add("Jeremiah LaForge ");
		nameList.add("Newton Hoac");
		nameList.add("Nozuko Sutherland");
		nameList.add("Christ Kaya");
		Collections.shuffle(nameList);
	//	System.out.println(nameList);
	}
	public void findName(int index) {
		System.out.println(nameList.get(index));
	}
	
	
}

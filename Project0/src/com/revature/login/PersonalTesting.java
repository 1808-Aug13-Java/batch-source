
package com.revature.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PersonalTesting {

	public static void main (String[] args) {

	    File file = new File("src\\com\\revature\\login\\LoginAccounts.txt");
	    int count=0;
	    try {

	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine() && count <7) {
	            String i = sc.nextLine();
//	            if (i.equals("banana")) {
//	            	System.out.println("this string already exists");
//	            }
	            System.out.println(i);
	            count++;
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	 }
	}
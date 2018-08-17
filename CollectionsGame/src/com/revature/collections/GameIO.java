package com.revature.collections;

import java.util.Scanner;

public class GameIO {

	private static Scanner sc = new Scanner(System.in);

	public void moveToNextPersonQuestion() {//void because not returning anything, just printing out
		System.out.println("Please press ENTER to move to next");
		String moveNext = sc.nextLine();
	}
}

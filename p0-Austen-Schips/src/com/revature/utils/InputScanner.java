package com.revature.utils;

import java.util.Scanner;

public class InputScanner {
	 private static Scanner readInput;
	    private static InputScanner input;

	    private InputScanner() {
	        readInput = new Scanner(System.in);
	    }

	    public static InputScanner getInstance() {
	        if(input == null) {
	            input = new InputScanner();
	        }

	        return input;
	    }

	    public String readInput() {
	        String inputString = readInput.nextLine();

	        return inputString;
	    }
}

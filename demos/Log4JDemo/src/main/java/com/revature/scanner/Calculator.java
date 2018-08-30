package com.revature.scanner;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Calculator {
	
	private static Scanner sc = new Scanner(System.in);
	private static Logger log = Logger.getRootLogger();
	
	public void calculate() {
		
		log.info("Please enter the operation you'd like to perform");
		String operation = sc.nextLine();
		
		int[] nums;
		int result=0;
		
		switch(operation) {
		case "addition":
			//get and add variables
			nums = getNums();
			result = nums[0]+nums[1];
			break;
		case "subtraction":
			//get and subtract variables
			nums = getNums();
			result = nums[0]-nums[1];
			break;
		case "division":
			//get and divide variables
			nums = getNums();
			while (nums[1]==0) {
				log.info("Cannot divide by 0, please enter valid operands.");
				nums = getNums();
			}
			result = nums[0]/nums[1];
			break;
		case "multiplication":
			//get and multiply variables
			nums = getNums();
			result = nums[0]*nums[1];
			break;
		default:
			log.info("Invalid operation");
			calculate();
		}
		
		log.info("Your result is: "+(result));
		
	}
	
	private int[] getNums() {
		int[] nums = new int[2];
		
		log.info("Please enter first number:");
		nums[0] = getNum();
		
		log.info("Please enter second number:");
		nums[1] = getNum();
		
		return nums;
	}
	
	private int getNum() {
		int num;
		try {
			num = Integer.parseInt(sc.nextLine());
			return num;
		} catch (NumberFormatException e) {
			log.info("Invalid input. Please input an integer");
			return getNum();
		}
	}

}

package com.revature.scanner;

import java.util.Scanner;

import org.apache.log4j.Logger;

public class Calculator {
	// System.in is our inputstream
	private static Scanner sc = new Scanner(System.in);
	private static Logger log = Logger.getRootLogger();
	
	public void calculate() {
		
		log.info("Please enter the operation you'd like to perform");
		String operation = sc.nextLine().toLowerCase();
		int[] nums;
		int result;
		
		switch(operation) {
			case "addition":
			case "+":
				// get and add vars
				nums = getNums();
				log.info(nums[0] + " + " + nums[1]
						+ " = " + (nums[0] + nums[1]));
				result = nums[0] + nums[1];
				break;
			case "subtraction":
			case "-":
				nums = getNums();
				log.info(nums[0] + " - " + nums[1]
						+ " = " + (nums[0] - nums[1]));
				result = nums[0] - nums[1];
				
				
				break;
			case "division":
			case "/":
				// get and divide
				nums = getNums();
				// ensure we don't run into ArithmeticException
				while (nums[1] == 0) {
					log.info("Can't divide by zero. "
							+ "Enter valid operands");
					
					nums = getNums();
				}
				result = nums[0] / nums[1];
				log.info(nums[0] + " / " + nums[1]
						+ " = " + (nums[0] / nums[1]));
				break;
			case "multiplication":
			case "*":
				// get and multiply
				nums = getNums();
				result = nums[0] * nums[1];
				log.info(nums[0] + " * " + nums[1]
						+ " = " + (nums[0] * nums[1]));
				break;
			default:
				log.info("Invalid operation");
				calculate();
				break;
		}
		
	}
	
	private int[] getNums() {
		int[] nums = new int[2];
		
		log.info("Please enter first number: ");
		nums[0] = getNum();
		
		log.info("Please enter second number: ");
		nums[1] = getNum();
		
		return nums;
	}
	
	private int getNum() {
		int num = 0;
		try {
			num = Integer.parseInt(sc.nextLine());
			return num;
		} catch (NumberFormatException e) {
			log.info("Invalid input, please enter an integer");
			return getNum();
		}
	}
	
	
}

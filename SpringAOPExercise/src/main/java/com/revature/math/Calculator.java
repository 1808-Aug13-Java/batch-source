package com.revature.math;

import org.springframework.stereotype.Component;

@Component()
public class Calculator {
	
	/**
	 * Returns the sum of the first number plus the second number. 
	 * @param n1 - number
	 * @param n2 - denominator
	 */
	public int add(int n1, int n2) {
		return n1 + n2;
	}
	
	/**
	 * Returns the difference of the second number subtracted from the first number. 
	 * @param n1 - subtracti
	 * @param n2 - subtracter
	 */
	public int subtract(int n1, int n2) {
		return n1 - n2;
	}
	
	/**
	 * Returns the product of the first number multiplied by the second number. 
	 * @param n1 - multiple
	 * @param n2 - multiple
	 */
	public int multiply(int n1, int n2) {
		return n1 * n2;
	}
	
	/**
	 * Returns the quotient of the first number divided by the second number. 
	 * @param n1 - numerator
	 * @param n2 - denominator
	 */
	public int divide(int n1, int n2) {
		return n1 / n2;
	}
	
	/**
	 * Returns the remainder of the first number divided by the second number. 
	 * @param n1 - numerator
	 * @param n2 - denominator
	 */
	public int remainder(int n1, int n2) {
		return n1 % n2;
	}
	
	/** 
	 * Returns the true modulo of two numbers, not the remainder. 
	 * @param n1 - The numerator
	 * @param n2 - The denominator
	 */
	public int modulo(int n1, int n2) {
		int result = n1 % n2;
		if (result < 0) {
			result += n2;
		}
		return result;
	}
}

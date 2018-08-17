package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {
	
	@Test
	public void emptyStringReturnsZero() {
		int sum =Calculator.add("");
		assertEquals(sum, 0);
	}
	
	@Test
	public void oneNumberReturnsItself() {
		int sum = Calculator.add("42");
		assertEquals(sum, 42);
	}
	
	@Test
	public void twoNumbersReturnSum() {
		int sum = Calculator.add("2,7");
		assertEquals(sum, 9);
	}
	
	/*@Test
	public void invalidInput() {
		int sum = Calculator.add("4, cat");
		assertEquals(sum, -1);
	}*/
	
	@Test(expected=NumberFormatException.class)
	public void invalidSingleInput() {
		assertEquals(Calculator.add("cat"), -1);
		
	}
	
	@Test
	public void twoNumbersNewLineReturnSum() {
		int sum = Calculator.add("5\n7");
		assertEquals(sum, 12);
	}

	

}

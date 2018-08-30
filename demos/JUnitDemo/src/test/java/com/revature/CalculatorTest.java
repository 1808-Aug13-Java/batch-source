package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void emptyStringReturnsZero() {
		int sum = Calculator.add("");
		assertEquals(sum,0);
	}
	
	@Test
	public void oneNumberReturnsItself() {
		int sum = Calculator.add("42");
		assertEquals(sum,42);
	}
	
	@Test
	public void twoNumbersReturnSum() {
		int sum = Calculator.add("22,79");
		assertEquals(sum,101);
	}
	
	@Test
	public void invalidInput() {
		int sum = Calculator.add("4,cat");
		assertEquals(sum,-1);
	}
	
//	@Test(expected=NumberFormatException.class)
//	public void invalidSingleInput() {
//		Calculator.add("cat");
//	}
	
	@Test
	public void invalidSingleInput() {
		assertEquals(Calculator.add("cat"),-1);
	}
	
}

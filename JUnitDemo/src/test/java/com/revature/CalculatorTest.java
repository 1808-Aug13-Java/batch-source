package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {
	
	@Test
	public void emptyStringReturnsZero() {
		
		int sum = Calculator.add("");
		assertEquals(sum, 0);
		
	}
	
	@Test 
	public void oneNumberReturnsItself() {
		
		int sum = Calculator.add("42");
		assertEquals(sum, 42);
		
	}
	
	@Test
	public void twoNumbersReturnSum() {
		int sum = Calculator.add("22,79");
		assertEquals(sum, 101);
	}
	
	@Test
	public void invalidInputWithMultiple() {
		int sum = Calculator.add("4\ncat,\n2");
		System.out.println();
		assertEquals(sum, -1);
	}
	
//	@Test(expected=NumberFormatException.class)
//	public void invalidSingleInput() {
//		Calculator.add("cat");
//	}
	
	@Test
	public void invalidSingleInput() {
		int sum = Calculator.add("Cat");
		assertEquals(sum, -1);
	}
	
	@Test(expected=RuntimeException.class)
	public void negativeNumberThrowsException() {
		Calculator.add("3,5\n-5");
	}
	
	@Test
	public void threeNumbersWithDifferentDelimitersReturnSum() {
		int sum = Calculator.add("2,4\n6");
		assertEquals(sum, 12);
	}
	
	@Test
	public void numbersGreaterThanThousandIgnored() {
		int sum = Calculator.add("1001\n2");
		assertEquals(sum, 2);
	}
	
	@Test
	public void singleLineDelimeter() {
		int sum = Calculator.add("//[##]/n5##7");
		assertEquals(sum, 12);
	}
}


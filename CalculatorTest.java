package com.revature;

import static org.junit.Assert.assertEquals;

import java.awt.geom.NoninvertibleTransformException;

import org.junit.Test;

public class CalculatorTest {
	
	@Test
	public void emptyStringReturnsZero() {
		int sum = Calculator.add(""); // if input is empty string...
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
	public void invalidInput() {
		int sum = Calculator.add("4,cat");
		assertEquals(sum, -1);
	}
	
//	@Test(expected=NumberFormatException.class)
//	public void invalidSingleInput() {
//		Calculator.add("cat");
//	}
	
	@Test
	public void invalidSingleInput() {
		assertEquals(Calculator.add("cat"), -1);
	}
	
	@Test
	public void twoNumbersNewlineDelimitedReturnsTheSum() {
		assertEquals(Calculator.add("34\n,5"), 39);
	}
	
	@Test
	public void threeNumbersDelimitedEitherWayReturnsTheSum() {
		assertEquals(Calculator.add("34,5,\n10"), 49);
	}
	
	@Test(expected=RuntimeException.class)
	public void NegativeNumbersThrowsException() {
		Calculator.add("-1");
	}
	
	@Test
	public void NumbersGreaterThan1000AreIgnored() {
		assertEquals(Calculator.add("1001,9"), 9);
	}
	
	@Test
	public void SingleCharDelimiterCanBeDefinedOnFirstLine() {
		
	}
	
}

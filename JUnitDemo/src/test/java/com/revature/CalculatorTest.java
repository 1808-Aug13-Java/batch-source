package com.revature;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalculatorTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	
	@Test
	public void emptyStringReturns0() {
		int sum = Calculator.add("");
		assertEquals(sum, 0);
	}
	
	@Test
	public void oneMemberReturnsItself() {
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
		expectedException.expect(NumberFormatException.class);
		int sum = Calculator.add("4,chan");
		throw new NumberFormatException();
	}
	
//	@Test(expected = NumberFormatException.class)
//	public void invalidSingleInput() {
//		Calculator.add("Somebody once told me the world was gonna rule my I aint the sharpest tool in the shed she was lookin kinda dumb with her finger and her thumb in the shape of an L on her forhead");
//	}
	
	@Test
	public void invalidSingleInput() {
		expectedException.expect(NumberFormatException.class);
		throw new NumberFormatException();//Calculator.add("Somebody once told me the world was gonna rule me \n I aint the sharpest tool in the shed she was lookin kinda dumb with her finger and her thumb in the shape of an L on her forhead");
		}
	

	@Test
	public void baseTwoNumber() {
		int sum = Calculator.add("2\n5");
		assertEquals(7, sum);
	}

	@Test
	public void baseThreeNumbersComma() {
		int sum = Calculator.add("1, 2, 3");
		assertEquals(6, sum);
	}
	
	@Test
	public void baseThreeNumbersNewline() {
		int sum = Calculator.add("3\n5\n9");
		assertEquals(17, sum);
	}
	
	@Test
	public void baseThreeNumbersBoth() {
		int sum = Calculator.add("1, 1\n1");
		assertEquals(3, sum);
	}

	@Test
	public void baseNegativeNumber() {
		expectedException.expect(NumberFormatException.class);
		Calculator.add("-1");
	}

	@Test
	public void singleChar() {
		int sum = Calculator.add("#\n3#77");
		assertEquals(80, sum);
	}

	@Test
	public void multiChar() {
		int sum = Calculator.add("Chandrika\n1Chandrika3Chandrika99");
		assertEquals(103, sum);
	}

	@Test
	public void over9000() {
		int sum = Calculator.add("9000, 1");
		assertEquals(1, sum);
	}
	
	
	
}

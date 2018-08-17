package com.revature.test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.revature.Numbers;

public class NumbersTest {
	
	@Test
	public void numberTestSum() {
		int sum = Numbers.returnTwoNumber("2\n3");
		assertEquals(sum,5);
	}
}

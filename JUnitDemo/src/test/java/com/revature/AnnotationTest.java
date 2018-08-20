package com.revature;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AnnotationTest {

	@BeforeClass
	public static void runBeforeClass() {//          NEEDS STATIC
		System.out.println("Run before class method");
	}
	
	@Before
	public void runBefore() {
		System.out.println("Run before every test");
	}
	
	@After
	public void runAfter() {
		System.out.println("Run after every test");
	}
	
	@AfterClass
	public static void runAfterClass() {//         NEEDS STATIC
		System.out.println("Run after every class");
	}
	
	@Test
	public void testOne() {
		System.out.println("called testOne method");
	}
	
	@Test
	public void testTwo() {
		System.out.println("called testTwo method");
	}
	
	@Test
	public void testThree() {
		System.out.println("called testThree method");
	}
	
	@Test
	public void testFour() {
		System.out.println("called testFour method");
	}
	
	@Test
	public void testFive() {
		System.out.println("called testFive method");
	}
}

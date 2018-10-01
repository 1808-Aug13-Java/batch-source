package com.revature;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class AnnotationTest {

	@BeforeClass
	public static void runBeforeClass() {
		System.out.println("called runBeforeClass method");
	}
	
	@Before
	public void runBefore() {
		System.out.println("called runBefore method");
	}
	
	@After
	public void runAfter() {
		System.out.println("called runAfter method");
	}
	
	@AfterClass
	public static void runAfterClass() {
		System.out.println("called runAfterClass method");
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
	
	
	
}

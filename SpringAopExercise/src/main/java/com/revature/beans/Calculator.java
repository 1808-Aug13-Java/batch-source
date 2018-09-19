package com.revature.beans;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
	
	public Calculator() {
		super();
	}


	public int add(int x, int y) {
		int value = x + y;
		System.out.println(value);
		return value;
	}
	
	public int subtract(int x, int y) {
		int value = x - y;
		System.out.println(value);
		return value;
	}
	
	public int multiply(int x, int y) {
		int value = x * y;
		System.out.println(value);
		return value;
	}
	
	public Integer divide(int x, int y) {
		int value = x / y;
		
		return value;
	}

	@Override
	public String toString() {
		return "Calculator [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}

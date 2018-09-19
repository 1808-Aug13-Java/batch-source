package com.revature.beans;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

	public int add(int x, int y) {
		return x + y;
	}
	public int subtract(int x, int y) {
		return x - y;
	}
	public int multiply(int x, int y) {
		return x*y;
	}
	public int divide(int x, int y) {
		return x/y;
	}
}

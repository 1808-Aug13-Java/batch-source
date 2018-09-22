package com.revature.models;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
	public Calculator() {}
	
	public double add(double x, double y) {
		return x + y;
	}
	
	public double minus(double x, double y) {
		return x - y;
	}
	
	public double multiply(double x, double y) {
		return x * y;
	}
	
	public double divide(double x, double y) {
		return x / y;
	}
	
	public double modulus(double x, double y) {
		return x % y;
	}
	
	public double exponent(double x, double y) {
		return Math.pow(x, y);
	}
}

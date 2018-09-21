package com.chandrika.beans;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
	private double result;
	
	public Calculator() {
		super();
	}

	public Calculator(double result) {
		super();
		this.result = result;
	}

	public double add(double a, double b) {
		return a+b;
	}
	
	public double subtract(double a, double b) {
		return a-b;
	}
	
	public double multiply(double a, double b) {
		return a*b;
	}
	
	public double divide(double a, double b) {
		try {
			return a/b;
		} catch (Exception e) {
			System.out.println("Can't divide by 0.");
		}
		return a;
	}

	@Override
	public String toString() {
		return "Calculator [result=" + result + "]";
	}
	
	
}

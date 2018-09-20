package com.revature.beans;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
	
	private boolean calculate = false;
	
	/**
	 * @return the calculate
	 */
	public boolean isCalculate() {
		return calculate;
	}

	/**
	 * @param calculate the calculate to set
	 */
	public void setCalculate(boolean calculate) {
		this.calculate = calculate;
	}

	public double add(double a, double b) {
		return (a+b);
	}
	
	public double subtract(double a, double b) {
		return (a-b);
		
	}
	
	public double multiply(double a, double b) {
		return (a*b);
		
	}
	
	public double divide(double a, double b) {
		double result = 0;
		if (b == 0) {
			throw new IllegalArgumentException("Cannot divide by 0");
		} else {
			result = (a/b);
		}
		return result;
		
	}

}

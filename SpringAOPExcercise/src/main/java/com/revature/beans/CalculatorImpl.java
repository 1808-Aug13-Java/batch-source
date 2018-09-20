package com.revature.beans;

import org.springframework.stereotype.Component;

@Component("Calculator")
public class CalculatorImpl implements Calculator{

	@Override
	public double add(double a, double b) {
		double result = a + b;
        System.out.println(a + " + " + b + " = " + result);
        return result;
		
	}

	@Override
	public double subtract(double a, double b) {
		double result = a - b;
        System.out.println(a + " - " + b + " = " + result);
        return result;
	}

	@Override
	public double multiply(double a, double b) {
		double result = a * b;
        System.out.println(a + " * " + b + " = " + result);
        return result;
	}

	@Override
	public double divide(double a, double b) {
		if(b == 0)
        {
            throw new IllegalArgumentException("b value must not be zero.");
        }
        double result = a / b;
        System.out.println(a + " / " + b + " = " + result);
        return result;
	}
	
}	

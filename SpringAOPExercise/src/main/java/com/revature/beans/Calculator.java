package com.revature.beans;

import org.springframework.stereotype.Component;

import com.revature.exceptions.DivideByZeroException;

@Component
public class Calculator {
	
	private double x,y, result;
	
	public double getX() {
		return x;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double add(double x, double y) {
		
		this.x = x;
		this.y =y;
		this.result = x+y;
		
		return result;
	}
	
	public double multiply(double x, double y) {
		
		this.x = x;
		this.y =y;
		this.result = x*y;
		
		return result;
	}
	
	public double subtract(double x, double y) {
		
		this.x = x;
		this.y =y;
		this.result = x-y;
		
		return x-y;
	}
	
	public double divide(double x, double y) throws RuntimeException {
		
		this.x = x;
		this.y =y;
		this.result = x/y;
		
		if(y == 0) {
			throw new DivideByZeroException();
		}
		
		return x/y;
	}

}

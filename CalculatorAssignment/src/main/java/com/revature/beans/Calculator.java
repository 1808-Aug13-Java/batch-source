package com.revature.beans;

import org.springframework.stereotype.Component;

@Component   //this is now a bean. logging aspect will track our methods from this bean
public class Calculator {
	private int a;
	private int b;
	
	
	public Calculator() {
		super();
	}
	public Calculator(int a, int b) {
		super();
		this.a = a;
		this.b = b;
		
	}
	
	public int add() { return a + b; }
	public int subtract() { return a - b; }
	public int divide() { return a / b; }
	public int multiply() { return a * b; }
	
	public int getA() {
		return a;
	}
	public int getB() {
		return b;
	}
	public void setA(int a) {
		this.a = a;
	}
	public void setB(int b) {
		this.b = b;
	}
	@Override
	public String toString() {
		return "Calculator [a=" + a + ", b=" + b + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + a;
		result = prime * result + b;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calculator other = (Calculator) obj;
		if (a != other.a)
			return false;
		if (b != other.b)
			return false;
		return true;
	}
	
	
}

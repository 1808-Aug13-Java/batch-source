package com.revature.beans;

import org.springframework.stereotype.Component;

@Component("calculator")
public class Calculator {

	private int number1;
	private String operation;
	private int number2;
	
	public Calculator() {
		super();
	}
	public Calculator(int number1, String operation, int number2) {
		super();
		this.number1 = number1;
		this.operation = operation;
		this.number2 = number2;
	}
	public int getNumber1() {
		return number1;
	}

	public void setNumber1(int number1) {
		this.number1 = number1;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getNumber2() {
		return number2;
	}

	public void setNumber2(int number2) {
		this.number2 = number2;
	}

	public int add(int number1, int number2) {
		return number1 + number2;
	}
	
	public int subtract(int number1, int number2) {
		return number1 - number2;
	}
	public int multiply(int number1, int number2) {
		return number1 * number2;
	}
	public int divide(int number1, int number2) {
		return number1 / number2;
	}

	@Override
	public String toString() {
		return "Calculator [number1=" + number1 + ", operation=" + operation + ", number2=" + number2 + "]";
	}


}

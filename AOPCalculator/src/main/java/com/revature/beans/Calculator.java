package com.revature.beans;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

	private int num1;
	private int num2;

	public Calculator() {
		super();
	}

	public Calculator(int num1, int num2) {
		super();
		this.num1 = num1;
		this.num2 = num2;
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public int addNums(int num1, int num2) {

		return num1 + num2;
	}

	public int subtractNums(int num1, int num2) {

		return num1 - num2;
	}

	public int multiplyNums(int num1, int num2) {

		return num1 * num2;
	}

	public int divideNums(int num1, int num2) {
		
		return num1/num2;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num1;
		result = prime * result + num2;
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
		if (num1 != other.num1)
			return false;
		if (num2 != other.num2)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Calculator [num1=" + num1 + ", num2=" + num2 + "]";
	}

}

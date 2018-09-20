package com.revature.beans;

import org.springframework.stereotype.Component;


@Component
public class Calculator {
	
	private int sumresult = 0;
	private int subresult = 0;
	private double divresult = 0;
	private int multresult = 0;
	
	public int add(int a, int b) {
		this.sumresult = a+b;
		return this.sumresult;
	}
	
	public int getSumresult() {
		return sumresult;
	}

	public void setSumresult(int sumresult) {
		this.sumresult = sumresult;
	}

	public int getSubresult() {
		return subresult;
	}

	public void setSubresult(int subresult) {
		this.subresult = subresult;
	}

	public double getDivresult() {
		return divresult;
	}

	public void setDivresult(double divresult) {
		this.divresult = divresult;
	}

	public int getMultresult() {
		return multresult;
	}

	public void setMultresult(int multresult) {
		this.multresult = multresult;
	}

	public int subtract(int a, int b) {
		this.subresult = a - b;
		return this.subresult;
	}
	
	public double divide(double a, double b) {
		System.out.println("in divide a is : "+ a);
		this.divresult = ((double)a) / b;
		System.out.println(this.divresult);
		return this.divresult;
	}
	
	public int multiply(int a, int b) {
		this.multresult = a*b;
		return this.multresult;
	}

	@Override
	public String toString() {
		return "Calculator [sumresult=" + sumresult + ", subresult=" + subresult + ", divresult=" + divresult
				+ ", multresult=" + multresult + "]";
	}

	

//	public Calculator() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

//	public Calculator(int sumresult, int subresult, double divresult, int multresult) {
//		super();
//		this.sumresult = sumresult;
//		this.subresult = subresult;
//		this.divresult = divresult;
//		this.multresult = multresult;
//	}
//	
	
	
	

}

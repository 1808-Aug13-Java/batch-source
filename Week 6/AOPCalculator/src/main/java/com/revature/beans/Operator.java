package com.revature.beans;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class Operator {
	private static Scanner sc= new Scanner(System.in);
	private float input1;
	private float input2;
	private float result;
	private String choice;
	
	public Operator() {
		super();
	}
	public Operator(float input1, float input2, float result) {
		super();
		this.input1 = input1;
		this.input2 = input2;
		this.result = result;
		
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public float getInput1() {
		return input1;
	}
	public void setInput1(float input1) {
		this.input1 = input1;
	}
	public float getInput2() {
		return input2;
	}
	public void setInput2(float input2) {
		this.input2 = input2;
	}
	public float getResult() {
		return result;
	}
	public void setResult(float result) {
		this.result = result;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(input1);
		result = prime * result + Float.floatToIntBits(input2);
		result = prime * result + Float.floatToIntBits(this.result);
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
		Operator other = (Operator) obj;
		if (Float.floatToIntBits(input1) != Float.floatToIntBits(other.input1))
			return false;
		if (Float.floatToIntBits(input2) != Float.floatToIntBits(other.input2))
			return false;
		if (Float.floatToIntBits(result) != Float.floatToIntBits(other.result))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Operator [input1=" + input1 + ", input2=" + input2 + ", result=" + result + "]";
	}
	
	public void compute(float in1, float in2, String operation) {
		switch(operation) {
			case "addition":{
				this.setResult(in1+in2);
				System.out.println(this.result);
				break;
			}
			case "multiplication":{
				this.setResult(in1*in2);
				System.out.println(this.result);
				break;
			}
			case "division":{
				this.setResult(in1/in2);
				System.out.println(this.result);
				break;
				}
			case "subtraction":{
				this.setResult(in1-in2);
				System.out.println(this.result);
				break;
			}
			default:{
				System.out.println("Please select a valid option");
//				System.exit(0);
				menu();
			}
		}
	}
	
	public void menu() {
		System.out.println("Please select an operation");
		String choice = sc.next();
		if (choice == "quit") {
			System.exit(0);
		}
		else {
			System.out.println("Enter your first input");
			float first= sc.nextFloat();
			System.out.println("Enter your second input");
			float second =sc.nextFloat();
			this.setInput1(first);
			this.setInput2(second);
			this.setChoice(choice);
//			compute(first, second, choice);
//			menu();
		}
	}
	
}

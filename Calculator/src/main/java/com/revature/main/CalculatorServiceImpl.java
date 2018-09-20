package com.revature.main;

public class CalculatorServiceImpl implements CalculatorService {

	@Override
	public int add(int j, int k) {
		return j + k;
	}

	@Override
	public int subtract(int j, int k) {
		return j -k;
	}

	@Override
	public int divide(int j, int k) {
		return (int) j/k;
	}

}

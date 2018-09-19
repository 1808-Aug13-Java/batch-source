package com.revature.beans;

import org.springframework.stereotype.Component;

@Component
public class Calculator 
{
	public Calculator()
	{
		
	}

	public int add(int x, int y)
	{
		return x + y;
	}
	
	public int sub(int x, int y)
	{
		return x - y;
	}
	
	public int mul(int x, int y)
	{
		return x * y;
	}
	
	public int div(int x, int y)
	{
		return x / y;
	}
	
	public int mod(int x, int y)
	{
		return x % y;
	}
	
	public int pow(int x, int y)
	{
		return (int) Math.pow(x, y);
	}
}

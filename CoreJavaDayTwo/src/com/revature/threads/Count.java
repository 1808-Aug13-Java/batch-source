package com.revature.threads;

public class Count {
	int count;
	
	public synchronized void increment() {
		count++;
	}
}

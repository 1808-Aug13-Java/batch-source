package com.revature.producerconsumer;

public class Basket {
	
	private int contents;
	private boolean available = false;
	
	public synchronized int get() {
		System.out.println("Contents: " + contents);
		while(!available) {
			try {
				
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		available = false;
		notify(); // notifies producer that it can stop waiting
		return contents;
	}
	
	public synchronized void put(int value) {
		System.out.println("Contents: " + contents);
		while(available) {
			try {
				System.out.println("\t\t\t" + Thread.currentThread().getName());
				System.out.println("Waiting...");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		contents = value;
		available = true;
		notify();
	}
	
}

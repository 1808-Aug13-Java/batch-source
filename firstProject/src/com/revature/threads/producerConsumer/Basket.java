package com.revature.threads.producerConsumer;

public class Basket {

	private int contents;
	private boolean available = false;
	
	public synchronized int get() {
		
		while(!available) {
			try {
				System.out.println("\t\t\t\t\t\t"+ Thread.currentThread().getName() + " is waiting");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		available = false;
		notify();
		return contents;
	}
	
	public synchronized void put(int value) {
		
		while(available) {
			try {
				System.out.println("\t\t\t\t\t\t"+ Thread.currentThread().getName() + " is waiting");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		contents = value;
		available = true;
		notify();
		
	}
	
}

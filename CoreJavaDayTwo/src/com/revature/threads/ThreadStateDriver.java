package com.revature.threads;

public class ThreadStateDriver {

	public static void main(String[] args) {
		Thread t1 = new TestThread();
		
		System.out.println("Thread state: " + t1.getState());
		System.out.println("isAlive? " + t1.isAlive());
		
		try {
			t1.start();
			System.out.println("Thread state: " + t1.getState());
			System.out.println("isAlive? " + t1.isAlive());
			System.out.println();
			t1.join(); // main method will wait until t1 is terminated
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Thread state: " + t1.getState());
		System.out.println("isAlive? " + t1.isAlive());
	}
	
}

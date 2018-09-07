package com.revature.threads;

public class ThreadStatesDiver {

	public static void main(String[] args) {
	
		Thread t1 = new TestThread();
		 System.out.println("Thead State: "+ t1.getState() + " isAlive? " + t1.isAlive());
		 
		 t1.start();
		 System.out.println("\nThead State: "+ t1.getState() + " isAlive? " + t1.isAlive());
		 
		 try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("\nThead State: "+ t1.getState() + " isAlive? " + t1.isAlive());

	}

}

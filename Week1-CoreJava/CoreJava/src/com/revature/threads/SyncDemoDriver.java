package com.revature.threads;

public class SyncDemoDriver {

	public static void main(String[] args) {
		
		/*
		 *using the StringTestRunnable as our job
		 *
		StringBuilder sbuild = new StringBuilder();
		StringBuffer sbuffer = new StringBuffer();
		Runnable job = new StringTestRunnable(sbuild, sbuffer);
		Thread t1 = new Thread(job);
		Thread t2 = new Thread(job);
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("StringBuilder result:");
		System.out.println(sbuild);
		System.out.println();
		System.out.println("StringBuffer result:");
		System.out.println(sbuffer);
		*/
		
		//using the CountTestRunnable as our job
		
		Count c = new Count();
		Runnable job = new CountTestRunnable(c);
		Thread t1 = new Thread(job);
		Thread t2 = new Thread(job);
		
		Runnable lambdaJob = ()->{
			System.out.println("This is the implementation of the Runnable"
					+ " interface's only method. \n We achieved this using a lambda!");
		};
		
		Thread t3 = new Thread(lambdaJob);
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Count: "+c.count);
		
	}

}

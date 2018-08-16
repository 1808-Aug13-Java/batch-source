package com.revature.threads;

public class SynchDemoDriver {
	
	public static void main(String[] args) {

		Count c = new Count();
		
		Runnable job = new CountTestRunnable(c);
		
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
		
		System.out.println("Count: " + c.count);
		
		
//		StringBuilder sbuild = new StringBuilder();
//		StringBuffer sbuffer = new StringBuffer();
//		
//		Runnable job = new StringTestRunnable(sbuild, sbuffer);
//		
//		Thread t1 = new Thread(job);
//		Thread t2 = new Thread(job);
//		
//		
//		t1.start();
//		t2.start();
//		
//		try {
//			t1.join();
//			t2.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		System.out.println("StringBuilder Result: ");
//		System.out.println(sbuild);
//		System.out.println();
//		System.out.println("StringBuffer Result:");
//		System.out.println(sbuffer);
		
		
		
		
		
	}
	
}

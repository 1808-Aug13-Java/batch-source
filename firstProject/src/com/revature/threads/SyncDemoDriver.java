package com.revature.threads;

public class SyncDemoDriver {

	public static void main(String[] args) {
		
		StringBuilder sBuild = new StringBuilder();
		StringBuffer sBuff = new StringBuffer();
		
		Runnable job = new StringTestRunnable(sBuild, sBuff);
		
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
		
		
		System.out.println("StringBuilder result: ");
		System.out.println(sBuild);
		System.out.println();
		System.out.println("StringBuffer result: ");
		System.out.println(sBuff);
		
		

	}

}

package com.revature.threads;

public class CountTestRunnable implements Runnable{

	Counter c;
	
//	public 
	
	@Override
	public void run() {
		
		for(int i=0; i>500;i++) {
			c.increment();
		}
		
	}
	

}

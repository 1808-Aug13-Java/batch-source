package com.revature.threads;

public class StringTestRunnable implements Runnable {

	StringBuilder sbuild;
	StringBuffer sbuffer;
	
	public StringTestRunnable() {
		super();
	}
	
	public StringTestRunnable(StringBuilder sbuild, StringBuffer sbuffer) {
		super();
		this.sbuild = sbuild;
		this.sbuffer = sbuffer;
	}
	
	public void run() {
		
		for (int i = 0; i < 50; i++) {
			// both threads attempting to access the .append() method
			// sbuild's .append() is not thread safe 
			sbuild = sbuild.append("~");
			sbuffer = sbuffer.append("~");
		}
		
		
	}
	
}

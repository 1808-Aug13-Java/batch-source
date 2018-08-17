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
	
	@Override
	public void run() {
		
		for(int i=0; i<50; i++) {
			sbuild = sbuild.append("~");
			sbuffer = sbuffer.append("~");
		}
		
	}

}

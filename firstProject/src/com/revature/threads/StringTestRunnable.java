package com.revature.threads;

public class StringTestRunnable implements Runnable{

	StringBuilder sBuild;
	StringBuffer sBuffer;
	
	public StringTestRunnable() {
		super();
	}
	
	public StringTestRunnable(StringBuilder sBuild, StringBuffer sBuffer) {
		super();
		this.sBuild = sBuild;
		this.sBuffer = sBuffer;
	}
	
	@Override
	public void run() {

		for(int i=0; i<50; i++) {
			sBuild = sBuild.append(" v");
			sBuffer = sBuffer.append(" v");
		}
		
	}

}

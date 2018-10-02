package com.revature.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BearWithAutomagic extends Bear {
	@Autowired
	private Cave cave;

	@Override
	public String toString() {
		return "BearWithAutomagic [cave=" + cave + "]";
	}
	
	@Override
	public void methodInBear() {
		System.out.println("method in BearWithAutomagic called. bear is: " + toString());
	}
}

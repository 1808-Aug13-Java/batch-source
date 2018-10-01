package com.revature.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BearWithAutomagic extends Bear{
	
	@Autowired //can put @Autowired over a property, a setter, or a constructor
	private Cave cave;

	@Override
	public String toString() {
		return "BearWithAutomagic [cave=" + cave + ", id=" + id + ", name=" + name + "]";
	}
	
	@Override
	public void methodInBear() {
		System.out.println("method in BearWithAutomagic called. bear is: "+toString());
	}

}

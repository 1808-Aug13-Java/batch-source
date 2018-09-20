package com.revature.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // same as putting the tag in the beans.xml	
public class BearWithAutomagic extends Bear {

	@Autowired //can be placed over a property, a setter or a constuctor, defaults to by type
	private Cave cave;

	@Override
	public String toString() {
		return "BearWithAutomagic [cave=" + cave + ", id=" + id + ",   name=" + name + "]";
	}
	
	@Override
	public void methodInBear() {
		System.out.println("bear with automagic " + toString());
	}
	
}

package com.revature;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Driver {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
		System.out.println(ac.getBean("bear"));

	}

}

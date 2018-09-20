package com.revature.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;

public class Driver {

	
	public static void main(String[] args) {
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Calculator c =  (Calculator) ac.getBean("calculator");
		c.add(4, 2);
		c.subtract(4, 6);
		c.divide(10, 5);
		
		c.multiply(2, 2);
//		c.divide(1, 0);
		c.divide(0, 1);
		
	}
}

package com.revature.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;

public class Driver {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext ac = 
				new ClassPathXmlApplicationContext("beans.xml");
		Calculator c = (Calculator) ac.getBean("calculator");
		
		c.add(3.4,5.2);
		c.subtract(3.4,5.2);
		c.divide(3.4,5.2);
		c.multiply(3.4,5.2);
		System.out.println(c.divide(3.4, 0.0));
	}
}

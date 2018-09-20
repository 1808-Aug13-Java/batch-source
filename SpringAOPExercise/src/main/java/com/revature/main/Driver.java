package com.revature.main;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;

public class Driver {
	
	public static void main(String[] args) {
				
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		
		Calculator c = (Calculator) ac.getBean("calculator");
		c.add(1, 7);
		c.subtract(5, 2);
		c.multiply(4, 2);
		c.divide(8, 2);
		c.divide(2, 0);
		
	}	

}

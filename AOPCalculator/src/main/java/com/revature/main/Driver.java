package com.revature.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;

public class Driver {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");		
		Calculator c = (Calculator) ac.getBean("calculator");
		c.addNums(8, 12);
		c.subtractNums(25, 2);
		c.divideNums(15, 3);
		c.multiplyNums(7, 3);
		c.divideNums(23, 0);
	}
}

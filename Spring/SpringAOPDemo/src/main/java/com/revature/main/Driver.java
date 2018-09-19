package com.revature.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;

public class Driver {

	public static void main(String[] args) {

		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Calculator c = (Calculator) ac.getBean("calculator");
		c.add(1,  2);
		c.subtract(2,  1);
		c.multiply(9, 5);
	}

}

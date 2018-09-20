package com.revature.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;

public class Driver {

	public static void main(String[] args) {

		// need to use application context to get an instance of our calculator
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Calculator c = (Calculator) ac.getBean("calculator");
		c.setCalculate(true);
		c.add(1, 2);
		c.subtract(5.6, 2.6);
		c.multiply(5, 3);
		//c.divide(8.0, 0);
		c.divide(8.0, 1);

	}

}

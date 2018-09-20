package com.revature.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;

public class Driver {

	public static void main(String[] args) {
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Calculator calculator = (Calculator) ac.getBean("calculator");
		calculator.add(2, 2);
		calculator.subtract(99, 30);
		calculator.multiply(100, 10);
		calculator.divide(1000, 20);
		
	}

}

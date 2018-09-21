package com.chandrika.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chandrika.beans.Calculator;

public class Driver {

	public static void main(String[] args) {
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Calculator c = (Calculator) ac.getBean("calculator");
		System.out.println(c.add(3, 6));
		System.out.println(c.subtract(3, 6));
		System.out.println(c.multiply(1.5, 1.5));
		System.out.println(c.divide(3, 6));
	}
}

package com.revature.main;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Calculator {
	private static Logger log = Logger.getRootLogger();
	private static Scanner console = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		CalculatorService calc = (CalculatorService) ac.getBean("calculatorService");
		log.info("Welcome to a super simple calculator.");
		calc.add(2, 3);
		calc.divide(0, 5);
		calc.divide(27, 3);
		calc.subtract(3, 10);
		calc.subtract(42, 3);
		
		ac.close();
		
	}
	
	
}

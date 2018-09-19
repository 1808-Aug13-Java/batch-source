package com.revature.main;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;

public class Driver {
    static Logger logger = Logger.getLogger(Driver.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Calculator calculator = (Calculator) ac.getBean("calculator");
		calculator.divide(5, 0);
	}
}

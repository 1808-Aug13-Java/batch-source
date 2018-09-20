package com.revature.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;

public class Driver {
	public static void main(String[] args){
	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    Calculator calculator = (Calculator) context.getBean("Calculator");
    double sum = calculator.add(10, 65);
    System.out.println(sum);
    double div = calculator.divide(12, 2);
    System.out.println(div);
	}
}

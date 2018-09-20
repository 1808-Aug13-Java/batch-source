package com.revature.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.math.Calculator;

public class Driver {
	
	public static void main(String[] args) {
		// Stupidly long code to initialize the beans. 
		try (ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml")) {
			// Remember, it is necessary to use a bean for aspects to work. 
			Calculator calcBean = (Calculator) ac.getBean(Calculator.class);
			
			calcBean.add(2, 5);
			calcBean.subtract(2, 5);
			calcBean.multiply(2, 5);
			calcBean.divide(5, 2);
			
			try {
				calcBean.divide(2, 0);
			} catch (ArithmeticException ex) {
				
			}
			
			calcBean.remainder(25, 4);
			calcBean.remainder(-25, 4);
			calcBean.modulo(25, 4);
			calcBean.modulo(-25, 4);
		}
	} // end of main
	
}

package com.revature.main;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.models.Calculator;

public class Driver {

	private static Logger log = Logger.getRootLogger();
	public static void main(String[] args) {
		log.info("##############START DRIVER################");
		
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Calculator calc = (Calculator) ac.getBean("calculator");
		double x = 5.0;
		double y = 2.0;
		double z = 0.0;
		
		calc.add(x, y);
		calc.minus(x, y);
		calc.multiply(x, y);
		calc.divide(x, y);
		calc.modulus(x, y);
		calc.exponent(x, y);
		
		calc.divide(y,z);
		
		log.info("###############END DRIVER#################");
	}

}

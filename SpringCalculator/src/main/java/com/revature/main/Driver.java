
package com.revature.main;

import org.apache.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Calculator;

public class Driver {

	private static Logger log = Logger.getRootLogger();

	public static void main(String[] args) {
		ConfigurableApplicationContext  ac = new ClassPathXmlApplicationContext("beans.xml");
		Calculator c = (Calculator) ac.getBean("calculator");
		log.info(c.add(23, 71));
		log.info(c.subtract(125, 36));
		log.info(c.multiply(15, 24));
		log.info(c.divide(48, 3));
		log.info(c.divide(3, 0));
		ac.close();
	}

}
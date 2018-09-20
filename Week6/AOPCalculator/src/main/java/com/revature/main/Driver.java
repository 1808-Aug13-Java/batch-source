package com.revature.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Operator;

public class Driver {

	public static void main(String[] args) {
		ConfigurableApplicationContext ac= new ClassPathXmlApplicationContext("beans.xml");
		Operator o= (Operator) ac.getBean("operator");
		o.menu();
		o.compute(o.getInput1(), o.getInput2(), o.getChoice());
	}

}

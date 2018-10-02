package com.revature.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Bear;

public class Driver {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Bear b = (Bear) ac.getBean("bear");
		Bear.setWinter(true);
		b.setFull(false);
		b.setFull(true);
//		b.bearHibernates();
		//b.bearSleeps();
//		System.out.println("After bear hibernates: "+b.isAwake());
//		b.wakeUpBear();
//		System.out.println("After we wake up our bear: "+b.isAwake());

	}

}
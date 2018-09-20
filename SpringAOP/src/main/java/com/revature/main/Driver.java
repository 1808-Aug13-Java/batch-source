package com.revature.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Bear;
import com.revature.beans.Calculater;

public class Driver {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		
		Calculater c = (Calculater) ac.getBean("calculater");
		//b.setFull(false);
		//System.out.println("bear full? "+ b.isFull());
		//System.out.println("bear awake? "+ b.isAwake());
		
		try {
      c.addOperation(1,4);
      c.divideOperation(1,4);
      c.divideOperation(1,0);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ac.close();
		

	}

}

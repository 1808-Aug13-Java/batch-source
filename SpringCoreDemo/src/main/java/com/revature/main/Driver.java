package com.revature.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Bear;
import com.revature.beans.BearWithAutomagic;

public class Driver {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
//		BearWithConstructor b1 = (BearWithConstructor) ac.getBean("bearWithConstructor");
//		
//		b1.methodInBear();
//		// .getBean() is looking for name of beans.xml bean
//		Bear b2 = (BearWithSetter) ac.getBean("bearWithSetter");
//		b2.methodInBear();
//		
//		Bear b3 = (BearAutowiringByName) ac.getBean("bearAutowiringByName");
//		b3.methodInBear();
//		
//		Bear b4 = (BearAutowiringByType) ac.getBean("bearAutowiringByType");
//		b4.methodInBear();
		
		Bear b5 = (BearWithAutomagic) ac.getBean("bearWithAutomagic");
		b5.methodInBear();
	}

}

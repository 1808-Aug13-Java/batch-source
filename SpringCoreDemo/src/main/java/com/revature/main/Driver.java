package com.revature.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Bear;
import com.revature.beans.BearAutowiringByName;
import com.revature.beans.BearAutowiringByType;
import com.revature.beans.BearWithAutomagic;
import com.revature.beans.BearWithConstructor;
import com.revature.beans.BearWithSetter;

public class Driver {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		BearWithConstructor b1 = (BearWithConstructor) ac.getBean("bearWithConstructor");
		b1.methodInBear();
		
		BearWithSetter b2 = (BearWithSetter) ac.getBean("bearWithSetter");
		b2.methodInBear();
		
		BearAutowiringByName b3 = (BearAutowiringByName) ac.getBean("bearAutowiringByName");
		b3.methodInBear();
		
		BearAutowiringByType b4 = (BearAutowiringByType) ac.getBean("bearAutowiringByType");
		b4.methodInBear();
		
		BearWithAutomagic b5 = (BearWithAutomagic) ac.getBean("bearWithAutomagic");
		b5.methodInBear();
	}

}

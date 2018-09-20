package com.revature.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Bear;
import com.revature.beans.BearAutoWiringByName;
import com.revature.beans.BearAutoWiringByType;
import com.revature.beans.BearWithAutomagic;
import com.revature.beans.BearWithConstructor;
import com.revature.beans.BearWithSetter;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ac= new ClassPathXmlApplicationContext("beans.xml");
//		BearWithConstructor b1= (BearWithConstructor) ac.getBean("bearWithConstructor");
//		b1.methodInBear();
//		
//		
//		Bear b2= (BearWithSetter) ac.getBean("bearWithSetter");
//		b2.methodInBear();
//		Bear b3= (BearAutoWiringByName) ac.getBean("bearAutoWiringByName");
//		b3.methodInBear();
//		Bear b4 =(BearAutoWiringByType) ac.getBean("bearAutoWiringByType");
//		b4.methodInBear();
		
		
		Bear b5 =(BearWithAutomagic) ac.getBean("bearWithAutomagic");
		b5.methodInBear();
	}
}
